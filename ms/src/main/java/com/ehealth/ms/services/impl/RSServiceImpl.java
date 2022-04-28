package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.*;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();

    private String urlRS = "http://localhost:8091/rs/";

    @Override
//    @PreAuthorize("hasAuthority('doctor:write')")
    public AuthResponseRSDTO getUserByUsername(String username) {
        AuthResponseRSDTO response = restTemplate.getForEntity(urlRS + "/getUserByEmail?email=" + username, AuthResponseRSDTO.class).getBody();
        return response;
    }

    @Override
    public boolean existUserByUsername(String username) {
        String response = restTemplate.getForEntity(urlRS + "/existUserByEmail?email=" + username, String.class).getBody();
        System.out.println("response from get: " + response);
        if(response.equals("yes")){
            return true;
        }
        return false;
    }

    @Override
    public void insertUser(RegisterUserRSDTO user) {
        restTemplate.postForEntity(urlRS + "/insertUser", user, RegisterUserRSDTO.class);
    }

    @Override
    public List<DoctorRSDTO> getAllDoctors(){
        DoctorRSDTO[] doctors = restTemplate.getForEntity(urlRS + "/doctors/getAll", DoctorRSDTO[].class).getBody();
        return Arrays.asList(doctors);
    }

    @Override
    public DoctorDetailsDTO getDoctorByEmail(String email){
        DoctorDetailsDTO doctorDetailsDTO = restTemplate.getForEntity(urlRS + "/getDoctorDetailsByEmail?email=" + email, DoctorDetailsDTO.class).getBody();
        return doctorDetailsDTO;
    }

    @Override
    public DoctorDetailsDTO createDoctor(NewDoctorDTO newDoctorDTO) {

        newDoctorDTO.setPassword(new BCryptPasswordEncoder(12).encode(newDoctorDTO.getPassword()));
        DoctorDetailsDTO doctorDetailsDTO = restTemplate.postForEntity(urlRS + "/doctors/new", newDoctorDTO, DoctorDetailsDTO.class).getBody();
        return doctorDetailsDTO;
    }

    @Override
    public void deleteDoctor(String email) {
        restTemplate.delete(urlRS + "/doctors?email=" + email);
    }

    @Override
    public DoctorDetailsDTO updateDoctor(UpdateDoctorRequestDTO updateDoctorRequestDTO) {
            AuthResponseRSDTO authResponseRSDTO = getUserByUsername(updateDoctorRequestDTO.getEmail());
            if(!new BCryptPasswordEncoder(12).encode(updateDoctorRequestDTO.getOldPassword()).equals(authResponseRSDTO.getPassword()))
                throw new RuntimeException("wrong password");
        DoctorDetailsDTO doctorDetailsDTO = restTemplate.postForEntity(urlRS + "/doctors/update", updateDoctorRequestDTO, DoctorDetailsDTO.class).getBody();
        return doctorDetailsDTO;
    }

    @Override
    public DoctorDetailsDTO updateDoctorAsAdmin(DoctorUpdateAdminDTO doctorDTO){
        DoctorDetailsDTO doctorDetailsDTO = restTemplate.postForEntity(urlRS + "/doctors/update", doctorDTO, DoctorDetailsDTO.class).getBody();
        return doctorDetailsDTO;
    }

    @Override
    public AdminDoctorEnums getAdminDoctorEnums() {
        AdminDoctorEnums adminDoctorEnums = restTemplate.getForEntity(urlRS + "/doctors/enums", AdminDoctorEnums.class).getBody();
        return adminDoctorEnums;
    }
}
