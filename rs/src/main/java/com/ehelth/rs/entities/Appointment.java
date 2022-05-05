package com.ehelth.rs.entities;


import com.ehelth.rs.entities.dto.AppointmentDTO;
import com.ehelth.rs.entities.dto.AppointmentDoctorDTO;
import com.ehelth.rs.entities.dto.AppointmentPatientDTO;
import com.ehelth.rs.entities.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "ehealth_sequence",
            sequenceName = "ehealth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehealth_sequence")
    @Column(name = "appointment_id")
    private long appointmentId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "doctor_email")
    @NonNull
    private User doctor;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "patient_email")
    @NonNull
    private User patient;

    @ManyToOne(targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_name")
    @NonNull
    private Hospital hospital;

    @Column(name = "date")
    @NonNull
    private LocalDateTime date;

    @Column(name = "duration")
    @NonNull
    private int duration;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    @NonNull
    private Status status;

    public AppointmentDoctorDTO toAppointmentDoctorDTO(){
        return AppointmentDoctorDTO.builder()
                .id(appointmentId + "")
                .hospital(hospital.getHospitalName())
                .date(date.toString())
                .duration(duration + "")
                .status(status.toString())
                .firstNamePatient(patient.getFirstName())
                .lastNamePatient(patient.getLastName())
                .middleNamePatient(patient.getMiddleName())
                .agePatient(patient.getAge() + "")
                .phoneNumberPatient(patient.getPhoneNumber())
                .build();
    }

    public AppointmentPatientDTO toAppointmentPatientDTO(){
        return AppointmentPatientDTO.builder()
                .id(appointmentId + "")
                .hospital(hospital.getHospitalName())
                .date(date.toString())
                .duration(duration + "")
                .status(status.toString())
                .firstNameDoctor(doctor.getFirstName())
                .lastNameDoctor(doctor.getLastName())
                .middleNameDoctor(doctor.getMiddleName())
                .emailDoctor(doctor.getEmail())
                .phoneNumberHospital(hospital.getPhoneNumber())
                .address(hospital.getAddress())
                .speciality(doctor.getSpeciality().toString())
                .price(doctor.getPrice() + "")
                .build();
    }
    public AppointmentDTO toAppointmentDTO(){
        return AppointmentDTO.builder()
                .id(appointmentId + "")
                .doctorEmail(doctor.getEmail())
                .patientEmail(patient.getEmail())
                .hospitalName(hospital.getHospitalName())
                .dateTime(date.toString())
                .duration(duration + "")
                .build();
    }
}
