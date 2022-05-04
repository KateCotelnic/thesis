package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;

public interface RSService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
}
