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
public class UserDetailsDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String age;
    private String phoneNumber;
    private String photo;
    private List<AppointmentPatientDTO> appointments;
}
