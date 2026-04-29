package com.pi.demo.services;

import com.pi.demo.entities.Animal;
import com.pi.demo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired

    private AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }
    public List<Animal> findByEspecieId(Long especieId) {
        return animalRepository.findByEspecieIdEspecie(especieId);
    }
    public List<Animal> findByNomeContaining(String nome) {
        return animalRepository.findByNomeAnimalContaining(nome);
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }
}