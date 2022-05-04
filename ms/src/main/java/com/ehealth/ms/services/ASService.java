package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.AppointmentDTO;

public interface ASService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
}
