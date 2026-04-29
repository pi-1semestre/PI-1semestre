package com.pi.demo.controllers;

import com.pi.demo.entities.Sexo;
import com.pi.demo.services.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sexo")
public class SexoController {



    @Autowired
    private SexoService sexoService;

    @GetMapping
    public ResponseEntity<List<Sexo>> getAllSexos() {
        return ResponseEntity.ok(sexoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sexo> getSexoById(@PathVariable Long id) {
        return sexoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sexo> createSexo(@RequestBody Sexo sexo) {
        return ResponseEntity.ok(sexoService.save(sexo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sexo> updateSexo(@PathVariable Long id, @RequestBody Sexo sexo) {
        if (!sexoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sexo.setIdSexo(id);
        return ResponseEntity.ok(sexoService.save(sexo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSexo(@PathVariable Long id) {
        if (!sexoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sexoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}