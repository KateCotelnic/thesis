package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.dto.RegisterUserRSDTO;

public interface RSService {
    AuthResponseRSDTO getUserByUsername(String username);
    boolean existUserByUsername(String username);
    void insertUser(RegisterUserRSDTO user);
}
