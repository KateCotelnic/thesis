package com.ehelth.rs.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeTimeForDoctorDTO {
    private String id;
    private String cronExpression;
}