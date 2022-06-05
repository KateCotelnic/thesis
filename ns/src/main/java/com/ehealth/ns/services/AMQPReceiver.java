package com.ehealth.ns.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AMQPReceiver {

    @RabbitListener(queues = AMQPConfiguration.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message as a generic AMQP 'Message' wrapper: {}" + message);
    }

}
