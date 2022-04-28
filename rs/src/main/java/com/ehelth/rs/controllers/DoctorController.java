package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.*;
import com.ehelth.rs.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rs/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/getAll")
    public ResponseEntity<DoctorDTO[]> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping("/new")
    public ResponseEntity<DoctorDetailsDTO> insertNewDoctor(@RequestBody NewDoctorDTO newDoctorDTO){
        return ResponseEntity.ok(doctorService.createDoctor(newDoctorDTO));
    }

    @DeleteMapping()
    public void deleteDoctor(@RequestParam(name = "email")String email){
        doctorService.delete(email);
    }

    @PostMapping("/update")
    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody DoctorUpdateAdminDTO doctorDTO){
        return ResponseEntity.ok(doctorService.update(doctorDTO));
    }

    @GetMapping("/enums")
    public ResponseEntity<AdminDoctorEnums> getEnumsForDoctor(){
        return ResponseEntity.ok(doctorService.getEnums());
    }
}
