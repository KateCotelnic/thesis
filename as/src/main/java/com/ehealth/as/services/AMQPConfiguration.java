package com.ehealth.as.services;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {
    public static final String EXCHANGE_NAME = "ehealthExchange";
    public static final String QUEUE_EMAIL_NAME = "mailQueue";
    public static final String QUEUE_APP_NAME = "appQueue";
    public static final String ROUTING_KEY_EMAIL = "emailQueue";
    public static final String ROUTING_KEY_APP = "appQueue";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_EMAIL_NAME, false);
    }

    @Bean
    public Queue appQueue() {
        return new Queue(QUEUE_APP_NAME, false);
    }

    @Bean
    public Binding declareBindingEmail() {
        return BindingBuilder.bind(emailQueue()).to(appExchange()).with(ROUTING_KEY_EMAIL);
    }

    @Bean
    public Binding declareBindingApp() {
        return BindingBuilder.bind(appQueue()).to(appExchange()).with(ROUTING_KEY_APP);
    }
}
