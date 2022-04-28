package com.ehealth.ms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorRequestDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String speciality;
    private String price;
    private String photo;
    private String grade;
    private String experience;
    private String description;
    private String classification;
    private List<String> hospitals;
}
