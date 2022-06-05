package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AppointmentDTO;
import com.ehealth.ms.entities.dto.FreeTimeDTO;
import com.ehealth.ms.entities.dto.RequestDeleteCommentDTO;
import com.ehealth.ms.services.ASService;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorController {
    private final RSService rsService;
    private final CurrentUserService currentUserService;
    private final ASService asService;

    @PostMapping("/deleteComment")
    public ResponseEntity<HttpStatus> deleteComment(@RequestBody RequestDeleteCommentDTO requestDeleteCommentDTO) {
        verifyIsDoctor();
        asService.requestDeleteComment(requestDeleteCommentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/acceptAppointment")
    public ResponseEntity<AppointmentDTO> acceptAppointment(@RequestParam(name = "id", defaultValue = "") String id) {
        verifyIsDoctor();
        return ResponseEntity.ok(asService.acceptAppointment(id));
    }

    @PostMapping("/declineAppointment")
    public ResponseEntity<AppointmentDTO> declineAppointment(@RequestParam(name = "id", defaultValue = "") String id) {
        verifyIsDoctor();
        return ResponseEntity.ok(asService.declineAppointment(id));
    }

    @PostMapping("/freeTime")
    public ResponseEntity<FreeTimeDTO> addFreeTime(@RequestBody FreeTimeDTO freeTimeDTO) {
        verifyIsDoctor();
        return ResponseEntity.ok(rsService.addFreeTime(freeTimeDTO));
    }

    @DeleteMapping("/freeTime")
    public ResponseEntity<HttpStatus> deleteFreeTime(@RequestParam(name = "id", defaultValue = "") String id) {
        verifyIsDoctor();
        rsService.deleteFreeTime(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void verifyIsDoctor() {
        if (!currentUserService.verifyDoctor()) {
            throw new RuntimeException("User is not doctor");
        }
    }
}
