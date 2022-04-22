package com.ehealth.ms.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDTO {
    private String email;
    private String password;
}
