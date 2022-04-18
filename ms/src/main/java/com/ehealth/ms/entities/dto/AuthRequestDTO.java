package com.ehealth.ms.entities.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthRequestDTO {
    private String email;
    private String password;
}
