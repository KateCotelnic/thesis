package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.*;

import java.util.List;

public interface RSService {
    AuthResponseRSDTO getUserByUsername(String username);
    boolean existUserByUsername(String username);
    void insertUser(RegisterUserRSDTO user);
    List<DoctorRSDTO> getAllDoctors();
    DoctorDetailsDTO getDoctorByEmail(String email);
    DoctorDetailsDTO createDoctor(NewDoctorDTO newDoctorDTO);
    void deleteDoctor(String email);
    DoctorDetailsDTO updateDoctor(UpdateDoctorRequestDTO updateDoctorRequestDTO);
    DoctorDetailsDTO updateDoctorAsAdmin(DoctorUpdateAdminDTO doctorDTO);
    AdminDoctorEnums getAdminDoctorEnums();
}
