package com.ehelth.rs.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDoctorDTO {
    private String hospital;
    private String date;
    private String duration;
    private String status;
    private String firstNamePatient;
    private String lastNamePatient;
    private String middleNamePatient;
    private String agePatient;
    private String phoneNumberPatient;
}
