package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.dto.PasswordDTO;
import com.ehealth.ms.entities.enums.Role;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import com.ehealth.ms.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {
    private final RSService rsService;
    private final AuthenticationManager authenticationManager;
    private final ValidationService validationService;

    public boolean verifyPatient() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
//        System.out.println("current user = " + username);
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if (user.getRole().equals(Role.PATIENT.name())) {
            return true;
        }
        return false;
    }

    @Override
    public AuthResponseRSDTO authenticate(String email, String password) {
        AuthResponseRSDTO user;
        try {
            user = rsService.getUserByUsername(email);
            System.out.println("//////////////////////");
            System.out.println(user);
            System.out.println("//////////////////////");
            if (!user.isEnable()) {
                throw new RuntimeException("User is not enable");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return user;
//        } catch (AuthenticationException e) {
        } catch (RuntimeException e) {
            throw new BadCredentialsException("Incorrect combination of email and/or password");
        }
    }

    public boolean verifyAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if (user.getRole().equals(Role.ADMIN.name())) {
            return true;
        }
        return false;
    }

    public boolean verifyDoctor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if (user.getRole().equals(Role.DOCTOR.name())) {
            return true;
        }
        return false;
    }

    @Override
    public void changePassword(String email, PasswordDTO passwordDTO) {
        System.out.println("email = " + email);
        System.out.println("password: " + passwordDTO);
        authenticate(email, passwordDTO.getOldPassword());
        validationService.verifyPassword(passwordDTO.getNewPassword());
        passwordDTO.setNewPassword(new BCryptPasswordEncoder(12).encode(passwordDTO.getNewPassword()));
        rsService.updatePassword(email, passwordDTO);
    }
}
