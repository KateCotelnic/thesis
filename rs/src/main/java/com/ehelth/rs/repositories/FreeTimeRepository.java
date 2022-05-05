package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.FreeTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FreeTimeRepository extends JpaRepository<FreeTime, Long> {
    @Transactional
    void deleteByFreetimeId(long id);
}
