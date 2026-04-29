package com.pi.demo.repositories;

import com.pi.demo.entities.Porte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorteRepository extends JpaRepository<Porte, Long> {
    // Custom queries can be added here if needed
}