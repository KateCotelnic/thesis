package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.dto.*;
import com.ehelth.rs.services.DoctorService;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rs")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping("/getAdminEmail")
    public ResponseEntity<String> getAdmin() {
        String admin = userService.getAdmin();
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserCredentialsDTO> getUser(@RequestParam(name = "email", defaultValue = "") String email) {
        UserCredentialsDTO user = userService.getUserCredentials(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@RequestParam(name = "email", defaultValue = "") String email) {
        UserDetailsDTO user = userService.getUserDetails(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/existUserByEmail")
    public ResponseEntity<String> existUser(@RequestParam(name = "email", defaultValue = "") String email) {
        String existence = userService.existUser(email);
        return ResponseEntity.ok(existence);
    }

    @PostMapping("/insertUser")
    public ResponseEntity<UserCredentialsDTO> insertUser(@RequestBody RegisterUserDTO registerUserDTO) {
        UserCredentialsDTO user = userService.insertUser(registerUserDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getDoctorDetailsByEmail")
    public ResponseEntity<DoctorDetailsDTO> getDoctorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        System.out.println("email = " + email);
        DoctorDetailsDTO doctorDetailsDTO = doctorService.getDoctorByEmail(email);
        System.out.println(doctorDetailsDTO);
        return ResponseEntity.ok(doctorDetailsDTO);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserDetailsDTO> updateUser(@RequestParam(name = "email", defaultValue = "") String email, @RequestBody UserDetailsDTO userDetailsDTO) {
        UserDetailsDTO user = userService.updateUser(email, userDetailsDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(name = "email", defaultValue = "") String email) {
        userService.deleteUser(email);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<HttpStatus> updatePassword(@RequestParam(name = "email", defaultValue = "") String email, @RequestBody PasswordDTO passwordDTO) {
        userService.setNewPassword(email, passwordDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getName")
    public ResponseEntity<String> getNameByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        User user = userService.getUserByEmail(email);
        String name = user.getFirstName();
        return ResponseEntity.ok(name);
    }

    @GetMapping("/getSpeciality")
    public ResponseEntity<String> getSpecialityByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        User user = userService.getUserByEmail(email);
        String name = user.getSpeciality().toString();
        return ResponseEntity.ok(name);
    }
}
