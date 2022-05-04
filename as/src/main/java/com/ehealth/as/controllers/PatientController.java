package com.ehealth.as.controllers;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.services.NSService;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/as/patients")
@RequiredArgsConstructor
public class PatientController {
    private final RSService rsService;
    private final NSService nsService;

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
        appointmentDTO = rsService.createAppointment(appointmentDTO);
        nsService.sendDoctorNewAppointment(appointmentDTO);
        return ResponseEntity.ok(appointmentDTO);
    }
}
