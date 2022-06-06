package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.dto.HospitalEnums;
import com.ehelth.rs.entities.dto.SearchEnums;
import com.ehelth.rs.entities.enums.Area;
import com.ehelth.rs.entities.enums.Classification;
import com.ehelth.rs.entities.enums.Speciality;
import com.ehelth.rs.repositories.HospitalRepository;
import com.ehelth.rs.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.getHospitalByHospitalName(name).orElseThrow();
    }

    @Override
    public HospitalDTO[] getAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals.stream().map(Hospital::toHospitalDTO).toArray(HospitalDTO[]::new);
    }

    @Override
    public SearchEnums getSearchEnums() {
        return SearchEnums.builder()
                .specialities(Arrays.stream(Speciality.values()).map(Enum::toString).collect(Collectors.toList()))
                .areas(Arrays.stream(Area.values()).map(Enum::toString).collect(Collectors.toList()))
                .classifications(Arrays.stream(Classification.values()).map(Enum::toString).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Hospital> getAllByArea(String area) {
        return hospitalRepository.getHospitalByCityArea(Area.valueOf(area));
    }

    @Override
    public void createHospital(HospitalDTO hospitalDTO) {
        hospitalRepository.save(hospitalDTO.toHospital());
    }

    @Override
    public void delete(String hospitalName) {
        Hospital hospital = hospitalRepository.getHospitalByHospitalName(hospitalName).orElseThrow();
        hospital.setEnable(false);
        hospitalRepository.save(hospital);
    }

    @Override
    public HospitalDTO update(HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalRepository.getHospitalByHospitalName(hospitalDTO.getHospitalName()).orElseThrow();
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setCityArea(Area.valueOf(hospitalDTO.getCityArea()));
        hospital.setPhoto(hospitalDTO.getPhoto());
        hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
        hospital.setWebsite(hospitalDTO.getWebsite());
        hospitalRepository.save(hospital);
        return hospital.toHospitalDTO();
    }

    @Override
    public HospitalEnums getHospitalsEnums() {
        return HospitalEnums.builder()
                .areas(new ArrayList<>(List.of(Arrays.toString(Area.values()))))
                .build();
    }
}
