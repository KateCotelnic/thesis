package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;

import java.util.List;

public interface RSService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    String getAdminEmail();
    CommentDTO getComment(String id);
    AppointmentDTO cancelAppointment(String id);
    AppointmentDTO acceptAppointment(String id);
    AppointmentDTO declineAppointment(String id);
    List<AppointmentDTO> getAllAppointments();
    void setSentNotification(String id);
    String getNameByEmail(String email);
    String getSpecialityByEmail(String email);
    String getHospitalAddress(String hospitalName);
}
