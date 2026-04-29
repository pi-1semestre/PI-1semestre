package com.pi.demo.controllers;

import com.pi.demo.entities.Animal;
import com.pi.demo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especie/{especieId}")
    public ResponseEntity<List<Animal>> getAnimalsByEspecie(@PathVariable Long especieId) {
        return ResponseEntity.ok(animalService.findByEspecieId(especieId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Animal>> searchAnimalsByNome(@RequestParam String nome) {
        return ResponseEntity.ok(animalService.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.save(animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        if (!animalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        animal.setIdAnimal(id);
        return ResponseEntity.ok(animalService.save(animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (!animalService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        animalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}