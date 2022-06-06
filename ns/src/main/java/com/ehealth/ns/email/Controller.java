package com.ehealth.ns.email;

import com.ehealth.ns.entities.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final EmailSender emailSender;

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody MessageDTO messageDTO){
        emailSender.sendEmail(messageDTO.getReceiverEmail(), messageDTO.getMessage(), messageDTO.getSubject());
        return ResponseEntity.ok("Send");
    }
}
