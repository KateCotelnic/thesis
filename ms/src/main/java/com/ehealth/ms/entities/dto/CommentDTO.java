package com.ehealth.ms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String id;
    private String body;
    private String rating;
    private String date;
    private String firstNamePatient;
    private String lastNamePatient;
    private String middleNamePatient;
    private String photoPatient;
}
