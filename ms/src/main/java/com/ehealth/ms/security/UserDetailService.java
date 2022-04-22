package com.ehealth.ms.security;

import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final RSService rsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rsService.getUserByUsername(username).toUserDetail();
    }
}
