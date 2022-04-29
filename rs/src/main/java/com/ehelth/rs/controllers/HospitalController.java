package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.dto.HospitalEnums;
import com.ehelth.rs.entities.dto.SearchEnums;
import com.ehelth.rs.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createHospital(@RequestBody HospitalDTO hospitalDTO){
        hospitalService.createHospital(hospitalDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/byname")
    public ResponseEntity<HospitalDTO> getByName(@RequestParam(name = "hospitalName", defaultValue = "")String hospitalName){
        return ResponseEntity.ok(hospitalService.getHospitalByName(hospitalName).toHospitalDTO());
    }

    @DeleteMapping()
    public void deleteHospital(@RequestParam(name = "hospitalName")String hospitalName){
        hospitalService.delete(hospitalName);
    }

    @PostMapping("/update")
    public ResponseEntity<HospitalDTO> updateHospital(@RequestBody HospitalDTO hospitalDTO){
        return ResponseEntity.ok(hospitalService.update(hospitalDTO));
    }

    @GetMapping("/hospitalEnums")
    public ResponseEntity<HospitalEnums> getHospitalEnums(){
        return ResponseEntity.ok(hospitalService.getHospitalsEnums());
    }
}
