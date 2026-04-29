package com.pi.demo.controllers;

import com.pi.demo.entities.Porte;
import com.pi.demo.services.PorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portes")
public class PorteController {

    @Autowired
    private PorteService porteService;

    @GetMapping
    public ResponseEntity<List<Porte>> getAllPortes() {
        return ResponseEntity.ok(porteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Porte> getPorteById(@PathVariable Long id) {
        return porteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Porte> createPorte(@RequestBody Porte porte) {
        return ResponseEntity.ok(porteService.save(porte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Porte> updatePorte(@PathVariable Long id, @RequestBody Porte porte) {
        if (!porteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        porte.setIdPorte(id);
        return ResponseEntity.ok(porteService.save(porte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePorte(@PathVariable Long id) {
        if (!porteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        porteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}