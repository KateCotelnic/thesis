package com.ehelth.rs.services;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.PasswordDTO;
import com.ehelth.rs.entities.dto.RegisterUserDTO;
import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.entities.dto.UserDetailsDTO;

public interface UserService {
    UserCredentialsDTO getUserCredentials(String email);
    UserCredentialsDTO insertUser(RegisterUserDTO userDTO);
    String existUser(String email);
    User getUserByEmail(String email);
    UserDetailsDTO getUserDetails(String email);
    UserDetailsDTO updateUser(String email, UserDetailsDTO userDetailsDTO);
    void deleteUser(String email);
    void setNewPassword(String email, PasswordDTO passwordDTO);
    String getAdmin();
}
