package com.ehealth.ms.security;

import com.ehealth.ms.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class UserDetail implements UserDetails {
    private final String username;
    private final String password;
    private final Set<SimpleGrantedAuthority> grantedAuthorities;
    private final boolean isEnable;
    private final Role role;

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnable;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grantedAuthorities=" + grantedAuthorities +
                ", isEnable=" + isEnable +
                '}';
    }
}
