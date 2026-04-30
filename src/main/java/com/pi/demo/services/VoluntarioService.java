package com.pi.demo.services;

import com.pi.demo.entities.Voluntario;
import com.pi.demo.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public List<Voluntario> findAll() {
        return voluntarioRepository.findAll();
    }

    public Optional<Voluntario> findById(Long id) {
        return voluntarioRepository.findById(id);
    }

    public Voluntario save(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public void deleteById(Long id) {
        voluntarioRepository.deleteById(id);
    }
}