package com.pi.demo.services;

import com.pi.demo.entities.Porte;
import com.pi.demo.repositories.PorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PorteService {


    @Autowired
    private PorteRepository porteRepository;

    public List<Porte> findAll() {
        return porteRepository.findAll();
    }

    public Optional<Porte> findById(Long id) {
        return porteRepository.findById(id);
    }
    public Porte save(Porte porte) {
        return porteRepository.save(porte);
    }

    public void deleteById(Long id) {
        porteRepository.deleteById(id);
    }
}