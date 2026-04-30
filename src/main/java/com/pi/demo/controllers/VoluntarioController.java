package com.pi.demo.controllers;

import com.pi.demo.entities.Voluntario;
import com.pi.demo.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voluntarios")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping
    public ResponseEntity<List<Voluntario>> getAllVoluntarios() {
        return ResponseEntity.ok(voluntarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> getVoluntarioById(@PathVariable Long id) {
        return voluntarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Voluntario> createVoluntario(@RequestBody Voluntario voluntario) {
        return ResponseEntity.ok(voluntarioService.save(voluntario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> updateVoluntario(@PathVariable Long id, @RequestBody Voluntario voluntario) {
        if (!voluntarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        voluntario.setIdVoluntario(id);
        return ResponseEntity.ok(voluntarioService.save(voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        if (!voluntarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        voluntarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}