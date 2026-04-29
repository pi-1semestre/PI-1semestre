package com.pi.demo.repositories;

import com.pi.demo.entities.EmailMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMensagemRepository extends JpaRepository<EmailMensagem, Long> {
    // Custom queries can be added here if needed
}