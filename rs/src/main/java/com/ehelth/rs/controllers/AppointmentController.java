package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.AppointmentDTO;
import com.ehelth.rs.entities.dto.AppointmentEnums;
import com.ehelth.rs.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rs/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/enums")
    public ResponseEntity<AppointmentEnums> getEnums(){
        return ResponseEntity.ok(appointmentService.getEnums());
    }

    @PostMapping("/new")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }

    @GetMapping("/cancel")
    public ResponseEntity<AppointmentDTO> cancelAppointment(@RequestParam(name = "id", defaultValue = "")String id){
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }

    @GetMapping("/accept")
    public ResponseEntity<AppointmentDTO> acceptAppointment(@RequestParam(name = "id", defaultValue = "")String id){
        return ResponseEntity.ok(appointmentService.acceptAppointment(id));
    }

    @GetMapping("/decline")
    public ResponseEntity<AppointmentDTO> declineAppointment(@RequestParam(name = "id", defaultValue = "")String id){
        return ResponseEntity.ok(appointmentService.declineAppointment(id));
    }
}
