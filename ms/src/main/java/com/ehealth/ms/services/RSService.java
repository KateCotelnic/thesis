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
    DoctorDetailsDTO updateDoctorAsAdmin(DoctorUpdateAdminDTO doctorDTO);
    AdminDoctorEnums getAdminDoctorEnums();
    List<HospitalDTO> getHospitals();
    SearchEnums getSearchEnums();
    List<DoctorDetailsDTO> getDoctorsByHospital(String hospitalName);
    List<DoctorDetailsDTO> getDoctorsByParam(String area, String classification, String speciality);
    HospitalDTO createHospital(HospitalDTO hospitalDTO);
    HospitalEnums getHospitalEnums();
    void deleteHospital(String hospitalName);
    HospitalDTO updateHospitalAsAdmin(HospitalDTO hospitalDTO);
    UserDetailsDTO getUserDetails(String username);
    UserDetailsDTO updateUser(String username, UserDetailsDTO userDetailsDTO);
    void deleteUser(String username);
    void updatePassword(String email, PasswordDTO passwordDTO);
    List<CommentDTO> getCommentsByDoctor(String doctorEmail);
    NewCommentDTO createComment(NewCommentDTO newCommentDTO);
    NewCommentDTO updateComment(UpdateCommentDTO updateCommentDTO);
    void deleteComment(String id);
}
