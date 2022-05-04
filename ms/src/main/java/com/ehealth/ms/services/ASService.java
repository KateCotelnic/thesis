package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.AppointmentDTO;
import com.ehealth.ms.entities.dto.RequestDeleteCommentDTO;

public interface ASService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    void requestDeleteComment(RequestDeleteCommentDTO requestDeleteCommentDTO);
}
