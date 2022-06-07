package com.ehealth.ms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPage {
    List<DoctorRSDTO> list;
    int totalPages;
    int totalElements;
}
