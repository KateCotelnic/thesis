package com.ehealth.ms.controllers;

import com.ehealth.ms.entities.dto.DoctorDetailsDTO;
import com.ehealth.ms.entities.dto.HospitalDTO;
import com.ehealth.ms.entities.dto.SearchEnums;
import com.ehealth.ms.services.CurrentUserService;
import com.ehealth.ms.services.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeneralController {
    private final CurrentUserService currentUserService;
    private final RSService rsService;

    @GetMapping("/doctor")
    public ResponseEntity<DoctorDetailsDTO> getDoctor(@RequestParam(name = "email", defaultValue = "") String email) {
        if (!(currentUserService.verifyAdmin() || currentUserService.verifyPatient())) {
            throw new RuntimeException("user don't have permissions");
        }
        return ResponseEntity.ok(rsService.getDoctorByEmail(email));
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalDTO>> getHospitals(){
        return ResponseEntity.ok(rsService.getHospitals());
    }

    @GetMapping("/searchenums")
    public ResponseEntity<SearchEnums> getSearchEnums(){
        return ResponseEntity.ok(rsService.getSearchEnums());
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDetailsDTO>> getDoctorsByHospital(@RequestParam(name = "hospitalName", defaultValue = "")String hospitalName){
        return ResponseEntity.ok(rsService.getDoctorsByHospital(hospitalName));
    }

    @GetMapping("/doctors/param")
    public ResponseEntity<List<DoctorDetailsDTO>> getDoctorsByParam(@RequestParam(name = "area", defaultValue = "")String area, @RequestParam(name = "speciality", defaultValue = "")String speciality, @RequestParam(name = "classification", defaultValue = "")String classification){
        return ResponseEntity.ok(rsService.getDoctorsByParam(area, classification, speciality));
    }
}
