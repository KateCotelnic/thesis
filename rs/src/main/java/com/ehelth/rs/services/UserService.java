package com.ehelth.rs.services;

import com.ehelth.rs.entities.dto.UserCredentialsDTO;

public interface UserService {
    UserCredentialsDTO getUserCredentials(String email);
}
