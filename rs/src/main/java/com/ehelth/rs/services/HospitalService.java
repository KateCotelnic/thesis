package com.ehelth.rs.services;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.dto.HospitalEnums;
import com.ehelth.rs.entities.dto.SearchEnums;

import java.util.List;

public interface HospitalService {
    Hospital getHospitalByName(String name);
    HospitalDTO[] getAll();
    SearchEnums getSearchEnums();
    List<Hospital> getAllByArea(String area);
    void createHospital(HospitalDTO hospitalDTO);
    void delete(String hospitalName);
    HospitalDTO update(HospitalDTO hospitalDTO);
    HospitalEnums getHospitalsEnums();
}
