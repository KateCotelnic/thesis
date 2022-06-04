package com.ehealth.as.services.impl;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.services.NSService;
import com.ehealth.as.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RSServiceImpl implements RSService {
    private final NSService nsService;
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

    @Scheduled(cron = "0 * * * * ?")
    public void setDoneAppointments() {
        List<AppointmentDTO> appointments = Arrays.asList(restTemplate.getForEntity(urlRS + "/appointment/getAll", AppointmentDTO[].class).getBody());
        for (AppointmentDTO appointment : appointments){
            LocalDateTime endDate = LocalDateTime.parse(appointment.getEndDate());
            if(appointment.getStatus().equals("APPROVED") && endDate.isBefore(LocalDateTime.now())){
                appointment = restTemplate.getForEntity(urlRS + "/appointment/done?id=" + appointment.getId(), AppointmentDTO.class).getBody();
            }
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void sendReminder() {
        List<AppointmentDTO> appointments = Arrays.asList(restTemplate.getForEntity(urlRS + "/appointment/getAll", AppointmentDTO[].class).getBody());
        for (AppointmentDTO appointment : appointments){
            LocalDateTime startDate = LocalDateTime.parse(appointment.getStartDate());
            if(appointment.getStatus().equals("APPROVED") && appointment.getSentNotification().equals("false") && startDate.isBefore(LocalDateTime.now().plusHours(24))){
                nsService.sendNotification(appointment);
                restTemplate.getForEntity(urlRS + "/appointment/notification?id=" + appointment.getId(), AppointmentDTO.class);
            }
        }
    }
}
