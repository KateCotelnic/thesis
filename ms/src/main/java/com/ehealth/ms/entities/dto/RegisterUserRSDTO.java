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
}
