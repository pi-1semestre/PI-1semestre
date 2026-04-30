package com.pi.demo.repositories;

import com.pi.demo.entities.EmailVoluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVoluntarioRepository extends JpaRepository<EmailVoluntario, Long> {
    // Custom queries can be added here if needed
}