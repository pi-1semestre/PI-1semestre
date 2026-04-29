package com.pi.demo.repositories;

import com.pi.demo.entities.Pelagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelagemRepository extends JpaRepository<Pelagem, Long> {
    // Custom queries can be added here if needed
}