package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Appointment;
import com.ehelth.rs.entities.dto.AppointmentDTO;
import com.ehelth.rs.entities.enums.Status;
import com.ehelth.rs.repositories.AppointmentRepository;
import com.ehelth.rs.services.AppointmentService;
import com.ehelth.rs.services.HospitalService;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final UserService userService;
    private final HospitalService hospitalService;
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentDTOtoAppointment(appointmentDTO);
        return appointmentRepository.save(appointment).toAppointmentDTO();
    }

    @Override
    public AppointmentDTO cancelAppointment(String id) {
        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(Long.parseLong(id));
        appointment.setStatus(Status.DECLINED);
        return appointmentRepository.save(appointment).toAppointmentDTO();
    }

    @Override
    public AppointmentDTO acceptAppointment(String id) {
        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(Long.parseLong(id));
        appointment.setStatus(Status.APPROVED);
        return appointmentRepository.save(appointment).toAppointmentDTO();
    }

    @Override
    public AppointmentDTO declineAppointment(String id) {
        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(Long.parseLong(id));
        appointment.setStatus(Status.DECLINED);
        return appointmentRepository.save(appointment).toAppointmentDTO();
    }

    private Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Appointment.builder()
                .doctor(userService.getUserByEmail(appointmentDTO.getDoctorEmail()))
                .patient(userService.getUserByEmail(appointmentDTO.getPatientEmail()))
                .hospital(hospitalService.getHospitalByName(appointmentDTO.getHospitalName()))
                .date(LocalDateTime.parse(appointmentDTO.getDateTime(),formatter))
                .duration(Integer.parseInt(appointmentDTO.getDuration()))
                .status(Status.REQUESTED)
                .build();
    }
}
