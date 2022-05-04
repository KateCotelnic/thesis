package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AppointmentDTO;
import com.ehealth.ms.entities.dto.UserDetailsDTO;
import com.ehealth.ms.services.ASService;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final RSService rsService;
    private final ASService asService;
    private final CurrentUserService currentUserService;

    @PostMapping("/newAppointment")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        verifyIsPatient();
        return ResponseEntity.ok(asService.createAppointment(appointmentDTO));
    }

    private void verifyIsPatient(){
        if(!currentUserService.verifyPatient()){
            throw new RuntimeException("User is not patient");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<UserDetailsDTO> updateUser(@RequestBody UserDetailsDTO userDetailsDTO){
        verifyIsPatient();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return ResponseEntity.ok(rsService.updateUser(username, userDetailsDTO));
    }

//    @DeleteMapping("/deleteAppointment")
//    public ResponseEntity<HttpStatus> deleteAppointment() {
//        verifyIsPatient();
//        asService.createAppointment(appointmentDTO);
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }
}
