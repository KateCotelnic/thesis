package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.*;
import com.ehealth.ms.exceptions.NoPermissionsException;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final RSService rsService;
    private final CurrentUserService currentUserService;

    @GetMapping("/doctors")
    public ResponseEntity<Page> getDoctors(@RequestParam(name = "page", defaultValue = "1") String page) {
        verifyIsAdmin();
        List<DoctorRSDTO> list = rsService.getAllDoctors();
        return ResponseEntity.ok(GeneralController.constructPage(page, list));
    }

    @PostMapping("/newdoctor")
    private ResponseEntity<DoctorDetailsDTO> insertNewDoctor(@RequestBody NewDoctorDTO newDoctorDTO) {
        verifyIsAdmin();
        DoctorDetailsDTO doctorDetailsDTO = rsService.createDoctor(newDoctorDTO);
        return ResponseEntity.ok(doctorDetailsDTO);
    }

    @DeleteMapping("/doctor")
    public ResponseEntity<HttpStatus> getDoctor(@RequestParam(name = "email", defaultValue = "") String email) {
        verifyIsAdmin();
        rsService.deleteDoctor(email);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/updatedoctor")
    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody DoctorUpdateAdminDTO doctorDTO) {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyDoctor())) {
            throw new NoPermissionsException("User doesn't have permission");
        }
        return ResponseEntity.ok(rsService.updateDoctorAsAdmin(doctorDTO));
    }

    private void verifyIsAdmin() {
        if (!currentUserService.verifyAdmin()) {
            throw new NoPermissionsException("User is not admin");
        }
    }

    @GetMapping("/enums")
    public ResponseEntity<AdminDoctorEnums> getEnumsForDoctors() {
        verifyIsAdmin();
        return ResponseEntity.ok(rsService.getAdminDoctorEnums());
    }

    @PostMapping("/newhospital")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO) {
        verifyIsAdmin();
        return ResponseEntity.ok(rsService.createHospital(hospitalDTO));
    }

    @GetMapping("/hospitalEnums")
    public ResponseEntity<HospitalEnums> getEnums() {
        verifyIsAdmin();
        return ResponseEntity.ok(rsService.getHospitalEnums());
    }

    @DeleteMapping("/hospital")
    public ResponseEntity<HttpStatus> delete(@RequestParam(name = "hospitalName", defaultValue = "") String hospitalName) {
        verifyIsAdmin();
        rsService.deleteHospital(hospitalName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/updatehospital")
    public ResponseEntity<HospitalDTO> updateHospital(@RequestBody HospitalDTO hospitalDTO) {
        verifyIsAdmin();
        return ResponseEntity.ok(rsService.updateHospitalAsAdmin(hospitalDTO));
    }
}
