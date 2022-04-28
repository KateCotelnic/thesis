package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.enums.Role;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final RSService rsService;

    public void verifyPatient(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("current user = " + username);
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if(!user.getRole().equals(Role.PATIENT.name())){
            throw new RuntimeException("not patient user");
        }
    }
}
