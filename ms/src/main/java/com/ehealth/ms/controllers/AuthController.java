package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AuthRequestDTO;
import com.ehealth.ms.entities.dto.AuthResponseDTO;
import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.security.JwtTokenProvider;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class AuthController {
    private final RSService rsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<AuthResponseDTO> loginDoctor(@RequestBody AuthRequestDTO request){
        AuthResponseRSDTO user;
        try {
            user = rsService.getUserByUsername(request.getEmail());
            if(!user.isEnable()){
                throw new RuntimeException("User is not enable");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        } catch (AuthenticationException e) {
        }catch (RuntimeException e){
            throw new BadCredentialsException("Incorrect combination of email and/or password");
        }
        String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole());

        return ResponseEntity.ok(AuthResponseDTO.builder()
                .email(request.getEmail())
                .token(token)
                .role(user.getRole())
                .build());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("HELLO");
    }
}
