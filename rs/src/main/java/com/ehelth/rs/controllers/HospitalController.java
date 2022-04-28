package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.dto.SearchEnums;
import com.ehelth.rs.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rs/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<HospitalDTO[]> getAllHospitals(){
        return ResponseEntity.ok(hospitalService.getAll());
    }

    @GetMapping("/enums")
    public ResponseEntity<SearchEnums> getAllEnumsForSearch(){
        return ResponseEntity.ok(hospitalService.getSearchEnums());
    }
}
