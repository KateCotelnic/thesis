package com.ehealth.ns.amqp;

import com.ehealth.ns.amqp.AMQPConfiguration;
import com.ehealth.ns.email.EmailSender;
import com.ehealth.ns.entities.dto.MessageDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AMQPReceiver {
    private final EmailSender emailSender;

    @RabbitListener(queues = AMQPConfiguration.QUEUE_NAME)
    public void receiveMessage(String json) {
        Gson g = new Gson();
        MessageDTO messageDTO = g.fromJson(json, MessageDTO.class);
        emailSender.sendEmail(messageDTO.getReceiverEmail(), messageDTO.getMessage(), messageDTO.getSubject());
    }
}
