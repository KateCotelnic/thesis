package com.ehelth.rs.entities.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCredentialsDTO {
    private String email;
    private String password;
}
