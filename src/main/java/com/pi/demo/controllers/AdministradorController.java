package com.pi.demo.controllers;

import com.pi.demo.entities.Administrador;
import com.pi.demo.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
public class AdministradorController {


    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        return ResponseEntity.ok(administradorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        return administradorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Administrador> getAdministradorByEmail(@PathVariable String email) {
        return administradorService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{nome}")
    public ResponseEntity<List<Administrador>> getAdministradoresByAnimalNome(@PathVariable String nome) {
        return ResponseEntity.ok(administradorService.findByAnimalNome(nome));
    }

    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(@RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.save(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        if (!administradorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administrador.setIdAdministrador(id);
        return ResponseEntity.ok(administradorService.save(administrador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        if (!administradorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}