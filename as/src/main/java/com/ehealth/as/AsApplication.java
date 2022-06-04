package com.ehealth.as;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsApplication.class, args);
    }

}
