package com.ehealth.as.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private String id;
    private String doctorEmail;
    private String patientEmail;
    private String hospitalName;
    private String startDate;
    private String endDate;
    private String status;
    private String sentNotification;
}