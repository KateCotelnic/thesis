package com.ehealth.ms.services;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.dto.PasswordDTO;

public interface CurrentUserService {
    boolean verifyAdmin();
    boolean verifyDoctor();
    boolean verifyPatient();
    AuthResponseRSDTO authenticate(String email, String password);
    void changePassword(String email, PasswordDTO passwordDTO);
}
