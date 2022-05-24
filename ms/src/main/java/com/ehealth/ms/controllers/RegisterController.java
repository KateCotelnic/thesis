package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.RegisterRequestDTO;
import com.ehealth.ms.entities.dto.RegisterResponseDTO;
import com.ehealth.ms.entities.dto.RegisterUserRSDTO;
import com.ehealth.ms.security.JwtTokenProvider;
import com.ehealth.ms.services.RSService;
import com.ehealth.ms.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RSService rsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ValidationService validationService;

    @PostMapping()
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request){
        System.out.println(request);
        validationService.verifyEmail(request.getEmail());
        validationService.verifyPassword(request.getPassword());
        if(rsService.existUserByUsername(request.getEmail())){
            throw new RuntimeException("existing user");
        }
        String encodedPas = new BCryptPasswordEncoder(12).encode(request.getPassword());
        RegisterUserRSDTO userRSDTO = RegisterUserRSDTO.builder()
                .email(request.getEmail())
                .password(encodedPas)
                .isEnable(true)
                .role("PATIENT")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .phoneNumber(request.getPhoneNumber())
                .photo(request.getPhoto())
                .build();
        rsService.insertUser(userRSDTO);
        String token = jwtTokenProvider.createToken(request.getEmail(), "PATIENT");
        RegisterResponseDTO response = RegisterResponseDTO.builder()
                .email(request.getEmail())
                .token(token)
                .build();
        return ResponseEntity.ok(response);
    }
}
