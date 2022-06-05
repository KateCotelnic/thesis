package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Appointment;
import com.ehelth.rs.entities.dto.AppointmentDTO;
import com.ehelth.rs.entities.dto.AppointmentEnums;
import com.ehelth.rs.entities.enums.Status;
import com.ehelth.rs.repositories.AppointmentRepository;
import com.ehelth.rs.services.AppointmentService;
import com.ehelth.rs.services.HospitalService;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public AppointmentEnums getEnums() {
        return AppointmentEnums.builder()
                .statuses(new ArrayList<>(List.of(Arrays.toString(Status.values()))))
                .build();
    }

    @Override
    public AppointmentDTO setDone(String id) {
        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(Long.parseLong(id));
        appointment.setStatus(Status.DONE);
        appointmentRepository.save(appointment);
        return appointment.toAppointmentDTO();
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return appointmentRepository.getAllBy().stream().map(Appointment::toAppointmentDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO sendNotification(String id) {
        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(Long.parseLong(id));
        appointment.setSentNotification(true);
        return appointmentRepository.save(appointment).toAppointmentDTO();
    }

    private Appointment appointmentDTOtoAppointment(AppointmentDTO appointmentDTO) {
        System.out.println(appointmentDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(userService.getUserByEmail(appointmentDTO.getDoctorEmail()));
        System.out.println(userService.getUserByEmail(appointmentDTO.getPatientEmail()));
        System.out.println(hospitalService.getHospitalByName(appointmentDTO.getHospitalName()));
        Appointment appointment = Appointment.builder()
                .doctor(userService.getUserByEmail(appointmentDTO.getDoctorEmail()))
                .patient(userService.getUserByEmail(appointmentDTO.getPatientEmail()))
                .hospital(hospitalService.getHospitalByName(appointmentDTO.getHospitalName()))
                .startDate(LocalDateTime.parse(appointmentDTO.getStartDate(), formatter))
                .endDate(LocalDateTime.parse(appointmentDTO.getEndDate(), formatter))
                .status(Status.REQUESTED)
                .sentNotification(false)
                .build();
        System.out.println(appointment);
        return appointment;
    }
}
