package com.ehealth.ms.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    PATIENTS_READ("patient:read"),
    PATIENTS_WRITE("patient:write"),;

    private final String permission;
}