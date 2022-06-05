package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.AuthRequestDTO;
import com.ehealth.ms.entities.dto.AuthResponseDTO;
import com.ehealth.ms.entities.dto.AuthResponseRSDTO;
import com.ehealth.ms.security.JwtTokenProvider;
import com.ehealth.ms.services.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class AuthController {
    private final CurrentUserService currentUserService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        AuthResponseRSDTO user = currentUserService.authenticate(request.getEmail(), request.getPassword());
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
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("HELLO");
    }
}
