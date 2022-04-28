package com.ehelth.rs.services;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.dto.SearchEnums;

import java.util.List;

public interface HospitalService {
    Hospital getHospitalByName(String name);
    HospitalDTO[] getAll();
    SearchEnums getSearchEnums();
    List<Hospital> getAllByArea(String area);
}
