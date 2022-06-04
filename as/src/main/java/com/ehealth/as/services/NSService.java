package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.entities.dto.RequestDeleteCommentDTO;

public interface NSService {
    void sendDoctorNewAppointment(AppointmentDTO appointmentDTO);
    void sendAdminRequestDeleteComment(CommentDTO commentDTO);
    void sendDoctorCanceledAppointment(AppointmentDTO appointmentDTO);
    void sendPatientAcceptAppointment(AppointmentDTO appointmentDTO);
    void sendPatientDeclineAppointment(AppointmentDTO appointmentDTO);
    void sendNotification(AppointmentDTO appointmentDTO);
}
