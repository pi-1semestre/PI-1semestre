package com.pi.demo.services;

import com.pi.demo.entities.EmailVoluntario;
import com.pi.demo.repositories.EmailVoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailVoluntarioService {

    @Autowired
    private EmailVoluntarioRepository emailVoluntarioRepository;

    public List<EmailVoluntario> findAll() {
        return emailVoluntarioRepository.findAll();
    }

    public Optional<EmailVoluntario> findById(Long id) {
        return emailVoluntarioRepository.findById(id);
    }

    public EmailVoluntario save(EmailVoluntario emailVoluntario) {
        return emailVoluntarioRepository.save(emailVoluntario);
    }

    public void deleteById(Long id) {
        emailVoluntarioRepository.deleteById(id);
    }
}