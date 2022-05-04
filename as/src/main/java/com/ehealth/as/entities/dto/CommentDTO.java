package com.ehealth.as.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String commentId;
    private String body;
    private String rating;
    private String date;
    private String patientEmail;
    private String doctorEmail;
    private String reason;
    private String adminEmail;
}
