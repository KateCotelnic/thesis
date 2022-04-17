package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rs")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserCred")
    public ResponseEntity<UserCredentialsDTO> getUser(@RequestParam(name = "email", defaultValue = "")String email){
        UserCredentialsDTO user = userService.getUserCredentials(email);
        return ResponseEntity.ok(user);
    }
}
