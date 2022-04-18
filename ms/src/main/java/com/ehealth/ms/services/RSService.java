package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.AuthRequestDTO;
import com.ehealth.ms.entities.dto.AuthResponseDTO;

public interface RSService {
    AuthResponseDTO getUserAuth(AuthRequestDTO authRequestDTO);
}
