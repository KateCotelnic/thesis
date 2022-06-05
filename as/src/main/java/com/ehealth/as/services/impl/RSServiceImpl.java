package com.ehealth.as.services.impl;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();

    private String urlRS = "http://rs:8091/rs";


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

    @Override
    public AppointmentDTO cancelAppointment(String id) {
        return restTemplate.getForEntity(urlRS + "/appointment/cancel?id=" + id, AppointmentDTO.class).getBody();
    }

    @Override
    public AppointmentDTO acceptAppointment(String id) {
        return restTemplate.getForEntity(urlRS + "/appointment/accept?id=" + id, AppointmentDTO.class).getBody();
    }

    @Override
    public AppointmentDTO declineAppointment(String id) {
        return restTemplate.getForEntity(urlRS + "/appointment/decline?id=" + id, AppointmentDTO.class).getBody();
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return Arrays.asList(restTemplate.getForEntity(urlRS + "/appointment/getAll", AppointmentDTO[].class).getBody());
    }

    @Override
    public void setSentNotification(String id) {
        restTemplate.getForEntity(urlRS + "/appointment/notification?id=" + id, AppointmentDTO.class);
    }

    @Override
    public String getNameByEmail(String email) {
        return restTemplate.getForEntity(urlRS + "/getName?email=" + email, String.class).getBody();
    }

    @Override
    public String getHospitalAddress(String hospitalName) {
        return restTemplate.getForEntity(urlRS + "/hospitals/getAddress?hospital=" + hospitalName, String.class).getBody();
    }

}
