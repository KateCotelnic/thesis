package com.ehelth.rs.services;

import com.ehelth.rs.entities.dto.AppointmentDTO;
import com.ehelth.rs.entities.dto.AppointmentEnums;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO cancelAppointment(String id);
    AppointmentDTO acceptAppointment(String id);
    AppointmentDTO declineAppointment(String id);
    AppointmentEnums getEnums();
    AppointmentDTO setDone(String id);
    List<AppointmentDTO> getAll();
    AppointmentDTO sendNotification(String id);
}
