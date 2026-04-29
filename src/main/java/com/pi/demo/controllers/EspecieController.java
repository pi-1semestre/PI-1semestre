package com.pi.demo.controllers;

import com.pi.demo.entities.Especie;
import com.pi.demo.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<Especie>> getAllEspecies() {
        return ResponseEntity.ok(especieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> getEspecieById(@PathVariable Long id) {
        return especieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especie> createEspecie(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.save(especie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especie> updateEspecie(@PathVariable Long id, @RequestBody Especie especie) {
        if (!especieService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        especie.setIdEspecie(id);
        return ResponseEntity.ok(especieService.save(especie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable Long id) {
        if (!especieService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        especieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}