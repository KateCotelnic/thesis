package com.ehealth.as.services.impl;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();

    private String urlRS = "http://localhost:8091/rs/";


    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        return restTemplate.postForEntity(urlRS + "/appointment/new", appointmentDTO, AppointmentDTO.class).getBody();
    }

    @Override
    public String getAdminEmail() {
        return restTemplate.getForEntity(urlRS + "/getAdminEmail", String.class).getBody();
    }

    @Override
    public CommentDTO getComment(String id) {
        return restTemplate.getForEntity(urlRS + "/comments?id=" + id, CommentDTO.class).getBody();
    }
}
