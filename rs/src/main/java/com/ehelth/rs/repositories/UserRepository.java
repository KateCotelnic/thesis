package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByEmail(String email);
    List<User> getAllByRole(String role);
}
