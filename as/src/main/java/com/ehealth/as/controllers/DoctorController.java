package com.ehealth.as.controllers;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.entities.dto.RequestDeleteCommentDTO;
import com.ehealth.as.services.NSService;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/as/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final NSService nsService;
    private final RSService rsService;

    @PostMapping("/requestDeleteComment")
    public ResponseEntity<HttpStatus> requestDeleteComment(@RequestBody RequestDeleteCommentDTO requestDeleteCommentDTO){
        String admin = rsService.getAdminEmail();
        CommentDTO commentDTO = rsService.getComment(requestDeleteCommentDTO.getCommentId());
        commentDTO.setReason(requestDeleteCommentDTO.getReason());
        commentDTO.setAdminEmail(admin);
        nsService.sendAdminRequestDeleteComment(commentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/acceptAppointment")
    public ResponseEntity<AppointmentDTO> acceptAppointment(@RequestParam(name = "id", defaultValue = "")String id){
        AppointmentDTO appointmentDTO = rsService.acceptAppointment(id);
        nsService.sendPatientAcceptAppointment(appointmentDTO);
        return ResponseEntity.ok(appointmentDTO);
    }

    @GetMapping("/declineAppointment")
    public ResponseEntity<AppointmentDTO> declineAppointment(@RequestParam(name = "id", defaultValue = "")String id){
        AppointmentDTO appointmentDTO = rsService.declineAppointment(id);
        nsService.sendPatientDeclineAppointment(appointmentDTO);
        return ResponseEntity.ok(appointmentDTO);
    }
}
