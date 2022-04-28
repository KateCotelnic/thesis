package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.DoctorDetailsDTO;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeneralController {
    private final CurrentUserService currentUserService;
    private final RSService rsService;

    @GetMapping("/doctor")
    public ResponseEntity<DoctorDetailsDTO> getDoctor(@RequestParam(name = "email", defaultValue = "") String email) {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyPatient())) {
            throw new RuntimeException("user don't have permissions");
        }
        return ResponseEntity.ok(rsService.getDoctorByEmail(email));
    }

}
