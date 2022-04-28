package com.ehelth.rs.services;

import com.ehelth.rs.entities.Hospital;

public interface HospitalService {
    Hospital getHospitalByName(String name);
}
