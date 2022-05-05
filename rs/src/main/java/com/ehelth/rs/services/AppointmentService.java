package com.ehelth.rs.services;

import com.ehelth.rs.entities.dto.AppointmentDTO;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO cancelAppointment(String id);
    AppointmentDTO acceptAppointment(String id);
    AppointmentDTO declineAppointment(String id);
}
