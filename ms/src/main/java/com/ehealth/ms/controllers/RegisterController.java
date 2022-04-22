package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AuthRequestDTO;
import com.ehealth.ms.entities.dto.RegisterResponseDTO;
import com.ehealth.ms.entities.dto.RegisterUserRSDTO;
import com.ehealth.ms.security.JwtTokenProvider;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RSService rsService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody AuthRequestDTO request){
        if(rsService.existUserByUsername(request.getEmail())){
            throw new RuntimeException("existing user");
        }
        String encodedPas = new BCryptPasswordEncoder(12).encode(request.getPassword());
        RegisterUserRSDTO userRSDTO = RegisterUserRSDTO.builder()
                .email(request.getEmail())
                .password(encodedPas)
                .isEnable(true)
                .role("PATIENT")
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
