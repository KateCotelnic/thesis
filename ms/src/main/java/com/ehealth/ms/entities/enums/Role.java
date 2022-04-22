package com.ehealth.ms.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Role {
    DOCTOR(Stream.of(Permission.DOCTOR_READ, Permission.DOCTOR_WRITE).collect(Collectors.toSet())),
    PATIENT(Stream.of(Permission.PATIENTS_READ, Permission.PATIENTS_WRITE).collect(Collectors.toSet()));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority>  getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}