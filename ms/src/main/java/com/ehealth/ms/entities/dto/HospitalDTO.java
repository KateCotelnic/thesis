package com.ehealth.ms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private String hospitalName;
    private String cityArea;
    private String photo;
    private String phoneNumber;
    private String website;
    private String address;
}
