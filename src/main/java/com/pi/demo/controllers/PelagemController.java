package com.pi.demo.controllers;

import com.pi.demo.entities.Pelagem;
import com.pi.demo.services.PelagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pelagens")
public class PelagemController {

   
    @Autowired
    private PelagemService pelagemService;

    @GetMapping
    public ResponseEntity<List<Pelagem>> getAllPelagens() {
        return ResponseEntity.ok(pelagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelagem> getPelagemById(@PathVariable Long id) {
        return pelagemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pelagem> createPelagem(@RequestBody Pelagem pelagem) {
        return ResponseEntity.ok(pelagemService.save(pelagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelagem> updatePelagem(@PathVariable Long id, @RequestBody Pelagem pelagem) {
        if (!pelagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pelagem.setIdPelagem(id);
        return ResponseEntity.ok(pelagemService.save(pelagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelagem(@PathVariable Long id) {
        if (!pelagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pelagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}