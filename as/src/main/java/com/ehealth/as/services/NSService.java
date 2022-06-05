package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface NSService {
    void sendDoctorNewAppointment(AppointmentDTO appointmentDTO) throws JsonProcessingException;
    void sendAdminRequestDeleteComment(CommentDTO commentDTO) throws JsonProcessingException;
    void sendDoctorCanceledAppointment(AppointmentDTO appointmentDTO) throws JsonProcessingException;
    void sendPatientAppointment(AppointmentDTO appointmentDTO, boolean accepted) throws JsonProcessingException;
}
