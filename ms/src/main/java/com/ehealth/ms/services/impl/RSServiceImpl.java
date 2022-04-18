package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.AuthRequestDTO;
import com.ehealth.ms.entities.dto.AuthResponseDTO;
import com.ehealth.ms.services.RSService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String urlRS = "http://localhost:8091/rs/";
    @Override
    public AuthResponseDTO getUserAuth(AuthRequestDTO authRequestDTO) {
        ResponseEntity<AuthResponseDTO> response = restTemplate.getForEntity(urlRS + "getUserCred?email=" + authRequestDTO.getEmail(), AuthResponseDTO.class);
        return response.getBody();
    }
}
