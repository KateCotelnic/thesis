package com.ehealth.ms.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write"),
    ADMIN_WRITE("admin:write");

    private final String permission;
}