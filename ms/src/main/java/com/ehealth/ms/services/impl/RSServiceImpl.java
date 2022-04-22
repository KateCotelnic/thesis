package com.ehealth.ms.services.impl;

import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.entities.dto.RegisterUserRSDTO;
import com.ehealth.ms.services.RSService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RSServiceImpl implements RSService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String urlRS = "http://localhost:8091/rs/";

    @Override
//    @PreAuthorize("hasAuthority('doctor:write')")
    public AuthResponseRSDTO getUserByUsername(String username) {
        AuthResponseRSDTO response = restTemplate.getForEntity(urlRS + "/getUserByEmail?email=" + username, AuthResponseRSDTO.class).getBody();
        return response;
    }

    @Override
    public boolean existUserByUsername(String username) {
        String response = restTemplate.getForEntity(urlRS + "/existUserByEmail?email=" + username, String.class).getBody();
        System.out.println("response from get: " + response);
        if(response.equals("yes")){
            return true;
        }
        return false;
    }

    @Override
    public void insertUser(RegisterUserRSDTO user) {
        restTemplate.postForEntity(urlRS + "/insertUser", user, RegisterUserRSDTO.class);
    }
}
