package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.RegisterUserDTO;
import com.ehelth.rs.entities.dto.UserCredentialsDTO;
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
        User user = userRepository.getUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return user.toUserCredentialsDTO();
    }

    @Override
    public UserCredentialsDTO insertUser(RegisterUserDTO userDTO) {
        User user = userDTO.toUser();
        System.out.println(user);
        userRepository.save(user);
        return user.toUserCredentialsDTO();
    }

    @Override
    public String existUser(String email){
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isPresent())
            return "yes";
        return "no";
    }
}
