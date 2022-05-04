package com.ehealth.ms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDTO {
    private String commentId;
    private String body;
    private String rating;
    private String date;
    private String patientEmail;
    private String doctorEmail;
}
