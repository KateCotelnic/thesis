package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.dto.HospitalDTO;
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

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.getHospitalByHospitalName(name);
    }

    @Override
    public HospitalDTO[] getAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals.stream().map(Hospital::toHospitalDTO).toArray(HospitalDTO[]::new);
    }

    @Override
    public SearchEnums getSearchEnums() {
        return SearchEnums.builder()
                .specialities(new ArrayList<>(List.of(Arrays.toString(Speciality.values()))))
                .areas(new ArrayList<>(List.of(Arrays.toString(Area.values()))))
                .classifications(new ArrayList<>(List.of(Arrays.toString(Classification.values()))))
                .build();
    }

    @Override
    public List<Hospital> getAllByArea(String area) {
        return hospitalRepository.getHospitalByCityArea(Area.valueOf(area));
    }
}
