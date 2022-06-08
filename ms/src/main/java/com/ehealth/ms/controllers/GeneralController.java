package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.*;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeneralController {
    private final CurrentUserService currentUserService;
    private final RSService rsService;

    @GetMapping("/doctor")
    public ResponseEntity<DoctorDetailsDTO> getDoctor(@RequestParam(name = "email", defaultValue = "") String email) {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyPatient() || currentUserService.verifyDoctor())) {
            throw new RuntimeException("user don't have permissions");
        }
        System.out.println("email = " + email);
        return ResponseEntity.ok(rsService.getDoctorByEmail(email));
    }

    @GetMapping("/hospitals")
    public ResponseEntity<Page> getHospitals(@RequestParam(name = "page", defaultValue = "1") String page) {
        List<HospitalDTO> list = rsService.getHospitals();
        return ResponseEntity.ok(constructPage(page, list));
    }

    @GetMapping("/searchenums")
    public ResponseEntity<SearchEnums> getSearchEnums() {
        return ResponseEntity.ok(rsService.getSearchEnums());
    }

    @GetMapping("/doctors")
    public ResponseEntity<Page> getDoctorsByHospital(@RequestParam(name = "hospitalName", defaultValue = "") String hospitalName, @RequestParam(name = "page", defaultValue = "1") String page) {
        List<DoctorRSDTO> list = rsService.getDoctorsByHospital(hospitalName);
        return ResponseEntity.ok(constructPage(page, list));
    }

    @GetMapping("/doctors/param")
    public ResponseEntity<Page> getDoctorsByParam(@RequestParam(name = "area", defaultValue = "") String area, @RequestParam(name = "speciality", defaultValue = "") String speciality, @RequestParam(name = "classification", defaultValue = "") String classification, @RequestParam(name = "page", defaultValue = "1") String page) {
        List<DoctorRSDTO> list = rsService.getDoctorsByParam(area, classification, speciality);
        return ResponseEntity.ok(constructPage(page, list));
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetailsDTO> getMyDetails() {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyPatient())) {
            throw new RuntimeException("user don't have permissions");
        }
        return ResponseEntity.ok(rsService.getUserDetails(getCurrentUsername()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser() {
        if (!(currentUserService.verifyDoctor() || currentUserService.verifyPatient())) {
            throw new RuntimeException("user don't have permissions");
        }
        rsService.deleteUser(getCurrentUsername());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody PasswordDTO passwordDTO) {
        currentUserService.changePassword(getCurrentUsername(), passwordDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/commentsByDoctor")
    public ResponseEntity<List<CommentDTO>> getComments(@RequestParam(name = "doctorEmail", defaultValue = "") String doctorEmail) {
        return ResponseEntity.ok(rsService.getCommentsByDoctor(doctorEmail));
    }

    @DeleteMapping("/deleteComment")
    public ResponseEntity<HttpStatus> deleteComments(@RequestParam(name = "id", defaultValue = "") String id) {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyPatient())) {
            throw new RuntimeException("user don't have permissions");
        }
        rsService.deleteComment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public static Page constructPage(String pageNumber, List list){
        int size = list.size();
        int totalPages = size / 10;
        if(list.size() % 10 != 0){
            totalPages ++;
        }
        if(Integer.parseInt(pageNumber) * 10 <= list.size()) {
            list = list.subList((Integer.parseInt(pageNumber) - 1) * 10, Integer.parseInt(pageNumber) * 10);
        }
        else {
            list = list.subList((Integer.parseInt(pageNumber) - 1) * 10, list.size());
        }
        Page page = Page.builder()
                .list(list)
                .totalElements(size)
                .totalPages(totalPages)
                .build();
        return page;
    }

}
