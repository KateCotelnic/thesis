package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.repositories.UserRepository;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserCredentialsDTO getUserCredentials(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        System.out.println(user);
        return user.toUserCredentialsDTO();
    }
}
