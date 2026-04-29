package com.pi.demo.services;

import com.pi.demo.entities.Pelagem;
import com.pi.demo.repositories.PelagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PelagemService {

    @Autowired
    private PelagemRepository pelagemRepository;

    public List<Pelagem> findAll() {
        return pelagemRepository.findAll();
    }

    public Optional<Pelagem> findById(Long id) {
        return pelagemRepository.findById(id);
    }

    public Pelagem save(Pelagem pelagem) {
        return pelagemRepository.save(pelagem);
    }

    public void deleteById(Long id) {
        pelagemRepository.deleteById(id);
    }
}