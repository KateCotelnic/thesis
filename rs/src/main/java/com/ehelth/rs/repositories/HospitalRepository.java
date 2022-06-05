package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.enums.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {
    Optional<Hospital> getHospitalByHospitalName(String name);

    List<Hospital> getHospitalByCityArea(Area area);
}
