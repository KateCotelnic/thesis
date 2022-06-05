package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment getAppointmentByAppointmentId(long id);

    List<Appointment> getAllBy();
}
