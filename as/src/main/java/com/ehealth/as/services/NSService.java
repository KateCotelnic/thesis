package com.ehealth.as.services;

import com.ehealth.as.entities.dto.AppointmentDTO;

public interface NSService {
    void sendDoctorNewAppointment(AppointmentDTO appointmentDTO);
}
