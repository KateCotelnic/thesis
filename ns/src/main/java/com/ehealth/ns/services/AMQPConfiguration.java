package com.ehealth.ns.services;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {
    public static final String EXCHANGE_NAME = "ehealthExchange";
    public static final String QUEUE_NAME = "mailQueue";
    public static final String ROUTING_KEY = "emailQueue";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Binding declareBindingEmail() {
        return BindingBuilder.bind(emailQueue()).to(appExchange()).with(ROUTING_KEY);
    }
}
