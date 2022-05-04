package com.ehealth.as.controllers;

import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.entities.dto.RequestDeleteCommentDTO;
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
@RequestMapping("/as/doctors")
@RequiredArgsConstructor
public class DoctorController {
    NSService nsService;
    RSService rsService;

    @PostMapping("/requestDeleteComment")
    public ResponseEntity<HttpStatus> requestDeleteComment(@RequestBody RequestDeleteCommentDTO requestDeleteCommentDTO){
        String admin = rsService.getAdminEmail();
        System.out.println(admin);
        CommentDTO commentDTO = rsService.getComment(requestDeleteCommentDTO.getCommentId());
        commentDTO.setReason(requestDeleteCommentDTO.getReason());
        commentDTO.setAdminEmail(admin);
        System.out.println(commentDTO);
        nsService.sendAdminRequestDeleteComment(commentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
