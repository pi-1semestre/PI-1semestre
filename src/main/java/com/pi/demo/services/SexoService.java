package com.pi.demo.services;

import com.pi.demo.entities.Sexo;
import com.pi.demo.repositories.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexoService {

   

    @Autowired
    private SexoRepository sexoRepository;

    public List<Sexo> findAll() {
        return sexoRepository.findAll();
    }

    public Optional<Sexo> findById(Long id) {
        return sexoRepository.findById(id);
    }

    public Sexo save(Sexo sexo) {
        return sexoRepository.save(sexo);
    }

    public void deleteById(Long id) {
        sexoRepository.deleteById(id);
    }
}