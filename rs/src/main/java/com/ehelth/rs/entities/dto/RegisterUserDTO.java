package com.ehelth.rs.entities.dto;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDTO {
    private String email;
    private String password;
    private String role;
    private boolean isEnable;

    public User toUser(){
        return User.builder()
                .email(email)
                .isEnable(isEnable)
                .password(password)
                .role(Role.valueOf(role))
                .build();
    }
}
