package com.ehealth.ns.controllers;

import com.ehealth.ns.services.AppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ns")
@RequiredArgsConstructor
public class AppointmentsController {
    private final AppointmentsService appointmentsService;

    @PostMapping("/")
}
