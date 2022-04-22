package com.ehelth.rs.entities;

import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Column(name = "ENABLE")
    private boolean isEnable;

    public UserCredentialsDTO toUserCredentialsDTO(){
        return UserCredentialsDTO.builder()
                .email(this.email)
                .password(this.password)
                .role(this.role.toString())
                .isEnable(this.isEnable)
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isEnable=" + isEnable +
                '}';
    }
}
