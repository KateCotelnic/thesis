package com.ehealth.ms.entities.dto;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRSDTO {
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
