package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.RequestDeleteCommentDTO;
import com.ehealth.ms.services.ASService;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorController {
    private final RSService rsService;
    private final CurrentUserService currentUserService;
    private final ASService asService;

    @PostMapping("/deleteComment")
    public ResponseEntity<HttpStatus> deleteComment(@RequestBody RequestDeleteCommentDTO requestDeleteCommentDTO){
        verifyIsDoctor();
        asService.requestDeleteComment(requestDeleteCommentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void verifyIsDoctor(){
        if(!currentUserService.verifyPatient()){
            throw new RuntimeException("User is not doctor");
        }
    }
}
