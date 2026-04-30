package com.pi.demo.repositories;

import com.pi.demo.entities.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    // Custom queries can be added here if needed
}