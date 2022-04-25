package com.ehealth.ms.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String role;
    private boolean isEnable;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String photo;
}
