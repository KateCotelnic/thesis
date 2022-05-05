package com.ehealth.as.controllers;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.services.NSService;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/cancelAppointment")
    public void cancel(@RequestParam(name = "id", defaultValue = "")String id){
        AppointmentDTO appointmentDTO = rsService.cancelAppointment(id);
        System.out.println(appointmentDTO);
        nsService.sendDoctorCanceledAppointment(appointmentDTO);
    }
}
