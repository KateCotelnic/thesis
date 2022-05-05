package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;

public interface RSService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    String getAdminEmail();
    CommentDTO getComment(String id);
    AppointmentDTO cancelAppointment(String id);
    AppointmentDTO acceptAppointment(String id);
    AppointmentDTO declineAppointment(String id);
}
