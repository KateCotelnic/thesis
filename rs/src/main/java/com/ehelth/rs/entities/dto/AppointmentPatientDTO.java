package com.ehelth.rs.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentPatientDTO {
    private String id;
    private String hospital;
    private String date;
    private String duration;
    private String status;
    private String firstNameDoctor;
    private String lastNameDoctor;
    private String middleNameDoctor;
    private String emailDoctor;
    private String phoneNumberHospital;
    private String address;
    private String speciality;
    private String price;
}
