package com.pi.demo.repositories;

import com.pi.demo.entities.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {
    // Custom queries can be added here if needed
}