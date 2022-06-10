package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.PasswordDTO;
import com.ehelth.rs.entities.dto.RegisterUserDTO;
import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.entities.dto.UserDetailsDTO;
import com.ehelth.rs.entities.enums.Role;
import com.ehelth.rs.repositories.UserRepository;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserCredentialsDTO getUserCredentials(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
        return user.toUserCredentialsDTO();
    }

    @Override
    public UserCredentialsDTO insertUser(RegisterUserDTO userDTO) {
        User user = userDTO.toUser();
        userRepository.save(user);
        return user.toUserCredentialsDTO();
    }

    @Override
    public String existUser(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent())
            return "yes";
        return "no";
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
    }

    @Override
    public UserDetailsDTO getUserDetails(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
        return user.toUserDetailsDTO();
    }

    @Override
    public UserDetailsDTO updateUser(String email, UserDetailsDTO userDetailsDTO) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setLastName(userDetailsDTO.getLastName());
        user.setMiddleName(userDetailsDTO.getMiddleName());
        user.setAge(Integer.parseInt(userDetailsDTO.getAge()));
        user.setPhoneNumber(userDetailsDTO.getPhoneNumber());
        user.setPhoto(userDetailsDTO.getPhoto());
        userRepository.save(user);
        return user.toUserDetailsDTO();
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
        user.setEnable(false);
        userRepository.save(user);
    }

    @Override
    public void setNewPassword(String email, PasswordDTO passwordDTO) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user with the provided email was found."));
        user.setPassword(passwordDTO.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public String getAdmin() {
        String email = userRepository.getFirstByRole(Role.ADMIN).orElseThrow(() -> new RuntimeException("No admin was found.")).getEmail();
        return email;
    }
}
