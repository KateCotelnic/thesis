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
    public ResponseEntity<DoctorDTO[]> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping("/new")
    public ResponseEntity<DoctorDetailsDTO> insertNewDoctor(@RequestBody NewDoctorDTO newDoctorDTO) {
        return ResponseEntity.ok(doctorService.createDoctor(newDoctorDTO));
    }

    @DeleteMapping()
    public void deleteDoctor(@RequestParam(name = "email") String email) {
        doctorService.delete(email);
    }

    @PostMapping("/update")
    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody DoctorUpdateAdminDTO doctorDTO) {
        return ResponseEntity.ok(doctorService.update(doctorDTO));
    }

    @GetMapping("/enums")
    public ResponseEntity<AdminDoctorEnums> getEnumsForDoctor() {
        return ResponseEntity.ok(doctorService.getEnums());
    }

    @GetMapping()
    public ResponseEntity<DoctorDTO[]> getByHospital(@RequestParam(name = "hospitalName", defaultValue = "") String hospitalName) {
        return ResponseEntity.ok(doctorService.getByHospital(hospitalName));
    }

    @PostMapping("/param")
    public ResponseEntity<DoctorDTO[]> getByParam(@RequestBody ParametersDoctorDTO parametersDoctorDTO) {
        return ResponseEntity.ok(doctorService.getWithParam(parametersDoctorDTO));
    }

    @PostMapping("/addFreeTime")
    public ResponseEntity<FreeTimeDTO> addFreeTime(@RequestBody FreeTimeDTO freeTimeDTO) {
        return ResponseEntity.ok(doctorService.addFreeTime(freeTimeDTO));
    }

    @DeleteMapping("/deleteFreeTime")
    public void deleteFreeTime(@RequestParam(name = "id", defaultValue = "") String id) {
        doctorService.deleteFreeTime(id);
    }
}
