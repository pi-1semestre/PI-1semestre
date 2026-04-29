package com.pi.demo.repositories;

import com.pi.demo.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    // Custom queries can be added here if needed
}