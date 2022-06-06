package com.ehealth.ns.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender sender;

    public void sendEmail(String email, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("eHealth");

        sender.send(simpleMailMessage);
    }
}
