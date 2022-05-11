package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.*;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();
//    private final ValidationService validationService;
//    private final CurrentUserService currentUserService;
//    private final AuthenticationManager authenticationManager;

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
    public DoctorDetailsDTO updateDoctorAsAdmin(DoctorUpdateAdminDTO doctorDTO){
        DoctorDetailsDTO doctorDetailsDTO = restTemplate.postForEntity(urlRS + "/doctors/update", doctorDTO, DoctorDetailsDTO.class).getBody();
        return doctorDetailsDTO;
    }

    @Override
    public AdminDoctorEnums getAdminDoctorEnums() {
        AdminDoctorEnums adminDoctorEnums = restTemplate.getForEntity(urlRS + "/doctors/enums", AdminDoctorEnums.class).getBody();
        return adminDoctorEnums;
    }

    @Override
    public List<HospitalDTO> getHospitals() {
        HospitalDTO[] hospitalDTOS = restTemplate.getForEntity(urlRS + "/hospitals", HospitalDTO[].class).getBody();
        return Arrays.stream(hospitalDTOS).collect(Collectors.toList());
    }

    @Override
    public SearchEnums getSearchEnums() {
        return restTemplate.getForEntity(urlRS + "/hospitals/enums", SearchEnums.class).getBody();
    }

    @Override
    public List<DoctorRSDTO> getDoctorsByHospital(String hospitalName) {
        DoctorRSDTO[] doctorDetailsDTOS = restTemplate.getForEntity(urlRS + "/doctors?hospitalName=" + hospitalName, DoctorRSDTO[].class).getBody();
        return Arrays.stream(doctorDetailsDTOS).collect(Collectors.toList());
    }

    @Override
    public List<DoctorRSDTO> getDoctorsByParam(String area, String classification, String speciality) {
        ParametersDoctorDTO parametersDoctorDTO = ParametersDoctorDTO.builder().area(area).classification(classification).speciality(speciality).build();
        DoctorRSDTO[] doctorDetailsDTOS = restTemplate.postForEntity(urlRS + "/doctors/param", parametersDoctorDTO, DoctorRSDTO[].class).getBody();
        return Arrays.stream(doctorDetailsDTOS).collect(Collectors.toList());
    }

    @Override
    public HospitalDTO createHospital(HospitalDTO hospitalDTO) {
        try {
            HospitalDTO hospital = restTemplate.getForEntity(urlRS + "/hospitals/byname?hospitalName=" + hospitalDTO.getHospitalName(), HospitalDTO.class).getBody();
        }catch (Exception e){
            restTemplate.postForEntity(urlRS + "/hospitals/new", hospitalDTO, HttpStatus.class);
            return hospitalDTO;
        }
        throw new RuntimeException("The hospital exists");
    }

    @Override
    public HospitalEnums getHospitalEnums() {
        return restTemplate.getForEntity(urlRS + "/hospitals/hospitalEnums", HospitalEnums.class).getBody();
    }

    @Override
    public void deleteHospital(String hospitalName) {
        restTemplate.delete(urlRS + "/hospitals?hospitalName=" + hospitalName);
    }

    @Override
    public HospitalDTO updateHospitalAsAdmin(HospitalDTO hospitalDTO) {
        return restTemplate.postForEntity(urlRS + "/hospitals/update", hospitalDTO, HospitalDTO.class).getBody();
    }

    @Override
    public UserDetailsDTO getUserDetails(String username) {
        return restTemplate.getForEntity(urlRS + "/getUserDetails?email=" + username, UserDetailsDTO.class).getBody();
    }

    @Override
    public UserDetailsDTO updateUser(String username, UserDetailsDTO userDetailsDTO) {
        return restTemplate.postForEntity(urlRS + "/updateUser?email=" + username, userDetailsDTO, UserDetailsDTO.class).getBody();
    }

    @Override
    public void deleteUser(String username) {
        restTemplate.delete(urlRS + "/deleteUser?email=" + username);
    }

    @Override
    public void updatePassword(String email, PasswordDTO passwordDTO) {
        restTemplate.postForEntity(urlRS + "/updatePassword?email=" + email, passwordDTO, HttpStatus.class);
    }

    @Override
    public List<CommentDTO> getCommentsByDoctor(String doctorEmail) {
        CommentDTO[] commentDTOS = restTemplate.getForEntity(urlRS + "/comments/getAllByDoctor?doctorEmail=" + doctorEmail, CommentDTO[].class).getBody();
        return Arrays.asList(commentDTOS);
    }

    @Override
    public NewCommentDTO createComment(NewCommentDTO newCommentDTO) {
        return restTemplate.postForEntity(urlRS + "/comments/new", newCommentDTO, NewCommentDTO.class).getBody();
    }

    @Override
    public NewCommentDTO updateComment(UpdateCommentDTO updateCommentDTO) {
        return restTemplate.postForEntity(urlRS + "/comments/update", updateCommentDTO, NewCommentDTO.class).getBody();
    }

    @Override
    public void deleteComment(String id) {
        restTemplate.delete(urlRS + "/comments/delete?id=" + id);
    }

    @Override
    public AppointmentEnums getAppointmentEnums() {
        return restTemplate.getForEntity(urlRS + "/appointment/enums", AppointmentEnums.class).getBody();
    }

    @Override
    public FreeTimeDTO addFreeTime(FreeTimeDTO freeTimeDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        freeTimeDTO.setDoctorEmail(username);
        return restTemplate.postForEntity(urlRS + "/doctors/addFreeTime", freeTimeDTO, FreeTimeDTO.class).getBody();
    }

    @Override
    public void deleteFreeTime(String id) {
        restTemplate.delete(urlRS + "/doctors/deleteFreeTime?id=" + id);
    }
}
