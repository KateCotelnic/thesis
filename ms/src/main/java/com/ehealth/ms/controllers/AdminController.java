package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.*;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final RSService rsService;
    private final CurrentUserService currentUserService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorRSDTO>> getDoctors(){
        verifyIsAdmin();
        return ResponseEntity.ok(rsService.getAllDoctors());
    }

    @PostMapping("/newdoctor")
    private ResponseEntity<DoctorDetailsDTO> insertNewDoctor(@RequestBody NewDoctorDTO newDoctorDTO){
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
    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody DoctorUpdateAdminDTO doctorDTO){
        verifyIsAdmin();
       return ResponseEntity.ok(rsService.updateDoctorAsAdmin(doctorDTO));
    }

    private void verifyIsAdmin(){
        if(!currentUserService.verifyAdmin()){
            throw new RuntimeException("User is not admin");
        }
    }

    @GetMapping("/enums")
    public ResponseEntity<AdminDoctorEnums> getEnumsForDoctors(){
        return ResponseEntity.ok(rsService.getAdminDoctorEnums());
    }
}
