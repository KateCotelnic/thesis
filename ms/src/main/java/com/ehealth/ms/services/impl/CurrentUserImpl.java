package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.enums.Role;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserImpl implements CurrentUserService {
    private final RSService rsService;

    public boolean verifyPatient(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        System.out.println("current user = " + username);
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if(user.getRole().equals(Role.PATIENT.name())){
            return true;
        }
        return false;
    }

    public boolean verifyAdmin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        System.out.println("current user = " + username);
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if(user.getRole().equals(Role.ADMIN.name())){
            return true;
        }
        return false;
    }

    public boolean verifyDoctor(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        System.out.println("current user = " + username);
        AuthResponseRSDTO user = rsService.getUserByUsername(username);
        if(user.getRole().equals(Role.DOCTOR.name())){
            return true;
        }
        return false;
    }
}
