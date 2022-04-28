package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.*;
import com.ehelth.rs.entities.enums.Classification;
import com.ehelth.rs.entities.enums.Grade;
import com.ehelth.rs.entities.enums.Role;
import com.ehelth.rs.entities.enums.Speciality;
import com.ehelth.rs.repositories.UserRepository;
import com.ehelth.rs.services.DoctorService;
import com.ehelth.rs.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final UserRepository userRepository;
    private final HospitalService hospitalService;

    @Override
    public DoctorDTO[] getAllDoctors() {
        List<User> doctors = userRepository.getDoctors();
        return doctors.stream().map(User::toDoctorDTO).toArray(DoctorDTO[]::new);
    }

    @Override
    public DoctorDetailsDTO getDoctorByEmail(String email){
        User doctor = userRepository.getUserByEmailAndRole(email, Role.DOCTOR).orElseThrow(() -> new RuntimeException("No such user"));
        DoctorDetailsDTO doctorDetailsDTO = doctor.toDoctorDetailsDTO();
        return doctorDetailsDTO;
    }

    @Override
    public DoctorDetailsDTO createDoctor(NewDoctorDTO newDoctorDTO) {
        User user = newDoctorToUser(newDoctorDTO);
        userRepository.save(user);
        return user.toDoctorDetailsDTO();
    }

    @Override
    public void delete(String email) {
        User doctor = userRepository.getUserByEmailAndRole(email, Role.DOCTOR).orElseThrow(RuntimeException::new);
        doctor.setEnable(false);
        userRepository.save(doctor);
    }

    @Override
    public DoctorDetailsDTO update(DoctorUpdateAdminDTO doctorDTO) {
        User doctor = userRepository.getUserByEmailAndRole(doctorDTO.getEmail(), Role.DOCTOR).orElseThrow(RuntimeException::new);
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setMiddleName(doctorDTO.getMiddleName());
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctor.setSpeciality(Speciality.valueOf(doctorDTO.getSpeciality()));
        doctor.setPrice(Integer.parseInt(doctorDTO.getPrice()));
        doctor.setPhoto(doctorDTO.getPhoto());
        doctor.setGrade(Grade.valueOf(doctorDTO.getGrade()));
        doctor.setExperience(Integer.parseInt(doctorDTO.getExperience()));
        doctor.setDescription(doctorDTO.getDescription());
        doctor.setClassification(Classification.valueOf(doctorDTO.getClassification()));
        doctor.setHospitals(doctorDTO.getHospitals().stream().map(hospitalService::getHospitalByName).collect(Collectors.toList()));
        userRepository.save(doctor);
        return doctor.toDoctorDetailsDTO();
    }

    @Override
    public AdminDoctorEnums getEnums() {
        return AdminDoctorEnums.builder()
                .classifications(new ArrayList<>(List.of(Arrays.toString(Classification.values()))))
                .grades(new ArrayList<>(List.of(Arrays.toString(Grade.values()))))
                .specialities(new ArrayList<>(List.of(Arrays.toString(Speciality.values()))))
                .build();
    }

    @Override
    public DoctorDetailsDTO[] getByHospital(String hospitalName) {
        Hospital hospital = hospitalService.getHospitalByName(hospitalName);
        List<User> doctors = userRepository.getAllByHospitalsContainingAndRole(hospital, Role.DOCTOR);
        return doctors.stream().map(User::toDoctorDetailsDTO).toArray(DoctorDetailsDTO[]::new);
    }

    @Override
    public DoctorDetailsDTO[] getWithParam(ParametersDoctorDTO parametersDoctorDTO) {
        List<User> doctors = new ArrayList<>();
        if(!(parametersDoctorDTO.getArea().isEmpty())){
            List<Hospital> hospitals = hospitalService.getAllByArea(parametersDoctorDTO.getArea());
            List<User> finalDoctors = doctors;
            hospitals.forEach(hospital -> finalDoctors.addAll(userRepository.getAllByHospitalsContaining(hospital)));
            if(!(parametersDoctorDTO.getClassification().isEmpty())){
                doctors.removeIf(doctor -> !doctor.getClassification().toString().equals(parametersDoctorDTO.getClassification()));
            }
            if(!(parametersDoctorDTO.getSpeciality().isEmpty())){
                doctors.removeIf(doctor -> !doctor.getSpeciality().toString().equals(parametersDoctorDTO.getSpeciality()));
            }
        }
        else if(!(parametersDoctorDTO.getSpeciality().isEmpty())){
            doctors = userRepository.getAllBySpeciality(Speciality.valueOf(parametersDoctorDTO.getSpeciality()));
            if(!(parametersDoctorDTO.getClassification().isEmpty())){
                doctors.removeIf(doctor -> !doctor.getClassification().toString().equals(parametersDoctorDTO.getClassification()));
            }
        }
        else if(!(parametersDoctorDTO.getClassification().isEmpty())){
            doctors = userRepository.getAllByClassification(Classification.valueOf(parametersDoctorDTO.getClassification()));
        }
        return doctors.stream().map(User::toDoctorDetailsDTO).toArray(DoctorDetailsDTO[]::new);
    }

    private User newDoctorToUser(NewDoctorDTO newDoctorDTO){
        return User.builder()
                .email(newDoctorDTO.getEmail())
                .password(newDoctorDTO.getPassword())
                .role(Role.DOCTOR)
                .isEnable(true)
                .firstName(newDoctorDTO.getFirstName())
                .lastName(newDoctorDTO.getLastName())
                .middleName(newDoctorDTO.getMiddleName())
                .age(0)
                .phoneNumber(newDoctorDTO.getPhoneNumber())
                .speciality(Speciality.valueOf(newDoctorDTO.getSpeciality()))
                .price(Integer.parseInt(newDoctorDTO.getPrice()))
                .photo(newDoctorDTO.getPhoto())
                .grade(Grade.valueOf(newDoctorDTO.getGrade()))
                .experience(Integer.parseInt(newDoctorDTO.getExperience()))
                .description(newDoctorDTO.getDescription())
                .classification(Classification.valueOf(newDoctorDTO.getClassification()))
                .rating(0)
                .commentsForDoctors(new ArrayList<>())
                .hospitals(newDoctorDTO.getHospitals().stream().map(hospitalService::getHospitalByName).collect(Collectors.toList()))
                .appointmentsForDoctor(new ArrayList<>())
                .build();
    }
}
