package com.ehelth.rs.services;

import com.ehelth.rs.entities.dto.*;

public interface DoctorService {
    DoctorDTO[] getAllDoctors();
    DoctorDetailsDTO getDoctorByEmail(String email);
    DoctorDetailsDTO createDoctor(NewDoctorDTO newDoctorDTO);
    void delete(String email);
    DoctorDetailsDTO update(DoctorUpdateAdminDTO doctorDTO);
    AdminDoctorEnums getEnums();
    DoctorDetailsDTO[] getByHospital(String hospitalName);
    DoctorDetailsDTO[] getWithParam(ParametersDoctorDTO parametersDoctorDTO);
}
