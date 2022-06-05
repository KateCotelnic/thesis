package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.User;
import com.ehelth.rs.entities.enums.Classification;
import com.ehelth.rs.entities.enums.Role;
import com.ehelth.rs.entities.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByEmail(String email);

    List<User> getAllByRole(Role role);

    Optional<User> getUserByEmailAndRole(String email, Role role);

    @Query(value = "SELECT * from users u where u.is_enable is TRUE and u.role = 'DOCTOR'", nativeQuery = true)
    List<User> getDoctors();

    List<User> getAllByHospitalsContainingAndRole(Hospital hospital, Role role);

    List<User> getAllByHospitalsContaining(Hospital hospitals);

    List<User> getAllBySpeciality(Speciality speciality);

    List<User> getAllByClassification(Classification classification);

    User getFirstByRole(Role role);
}
