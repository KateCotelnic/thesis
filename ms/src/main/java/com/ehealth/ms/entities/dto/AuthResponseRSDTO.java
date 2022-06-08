package com.ehealth.ms.entities.dto;

import com.ehealth.ms.entities.enums.Role;
import com.ehealth.ms.security.UserDetail;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseRSDTO {
    private String email;
    private String password;
    private String role;
    private boolean isEnable;
    private String firstName;
    private String lastName;
    private String middleName;

    public UserDetail toUserDetail(){
        return UserDetail.builder()
                .username(email)
                .isEnable(isEnable)
                .grantedAuthorities(Role.valueOf(role).getAuthorities())
                .password(password)
                .build();
    }
}
