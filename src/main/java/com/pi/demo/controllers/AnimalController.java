package com.pi.demo.controllers;

import com.pi.demo.entities.Animal;
import com.pi.demo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @GetMapping("/porte/{porteId}")
    public ResponseEntity<List<Animal>> getAnimalsByPorte(@PathVariable Long porteId) {
        return ResponseEntity.ok(animalService.findByPorteId(porteId));
    }

    @GetMapping("/pelagem/{pelagemId}")
    public ResponseEntity<List<Animal>> getAnimalsByPelagem(@PathVariable Long pelagemId) {
        return ResponseEntity.ok(animalService.findByPelagemId(pelagemId));
    }

    @GetMapping("/sexo/{sexoId}")
    public ResponseEntity<List<Animal>> getAnimalsBySexo(@PathVariable Long sexoId) {
        return ResponseEntity.ok(animalService.findBySexoId(sexoId));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Animal>> getAnimalsByStatus(@PathVariable boolean status) {
        return ResponseEntity.ok(animalService.findByStatusTrue());
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