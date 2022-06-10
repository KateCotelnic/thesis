package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.AppointmentDTO;
import com.ehealth.ms.entities.dto.RequestDeleteCommentDTO;
import com.ehealth.ms.exceptions.ASRestTemplateResponseErrorHandler;
import com.ehealth.ms.services.ASService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ASServiceImpl implements ASService {
    private final RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    private final RestTemplate restTemplate = restTemplateBuilder.errorHandler(new ASRestTemplateResponseErrorHandler()).build();

    private String urlAS = "http://as:8092/as/";


    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        return restTemplate.postForEntity(urlAS + "/patients/appointment", appointmentDTO, AppointmentDTO.class).getBody();
    }

    @Override
    public void requestDeleteComment(RequestDeleteCommentDTO requestDeleteCommentDTO) {
        restTemplate.postForEntity(urlAS + "/doctors/requestDeleteComment", requestDeleteCommentDTO, HttpStatus.class);
    }

    @Override
    public void cancelAppointment(String id) {
        restTemplate.delete(urlAS + "/patients/cancelAppointment?id=" + id);
    }

    @Override
    public AppointmentDTO acceptAppointment(String id) {
        return restTemplate.getForEntity(urlAS + "/doctors/acceptAppointment?id=" + id, AppointmentDTO.class).getBody();
    }

    @Override
    public AppointmentDTO declineAppointment(String id) {
        return restTemplate.getForEntity(urlAS + "/doctors/declineAppointment?id=" + id, AppointmentDTO.class).getBody();
    }
}
