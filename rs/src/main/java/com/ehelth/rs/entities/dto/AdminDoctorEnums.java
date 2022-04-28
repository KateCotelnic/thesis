package com.ehelth.rs.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDoctorEnums {
    List<String> specialities;
    List<String> grades;
    List<String> classifications;
}