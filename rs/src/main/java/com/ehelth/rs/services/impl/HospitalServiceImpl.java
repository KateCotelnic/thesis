package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.repositories.HospitalRepository;
import com.ehelth.rs.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.getHospitalByHospitalName(name);
    }
}
