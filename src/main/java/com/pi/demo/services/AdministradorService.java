package com.pi.demo.services;

import com.pi.demo.entities.Administrador;
import com.pi.demo.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {


    @Autowired
    
    private AdministradorRepository administradorRepository;

    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> findById(Long id) {
        return administradorRepository.findById(id);
    }

    public Optional<Administrador> findByEmail(String email) {
        return Optional.ofNullable(administradorRepository.findByEmailAdm(email));
    }

    public void deleteById(Long id) {
        administradorRepository.deleteById(id);
    }

    public List<Administrador> findByAnimalNome(String nome) {
        return administradorRepository.findByAnimalNome(nome);
    }

    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }
}