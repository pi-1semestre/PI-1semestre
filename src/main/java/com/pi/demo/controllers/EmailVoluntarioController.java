package com.pi.demo.controllers;

import com.pi.demo.entities.EmailVoluntario;
import com.pi.demo.services.EmailVoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emailVoluntarios")
public class EmailVoluntarioController {

    @Autowired
    private EmailVoluntarioService emailVoluntarioService;

    @GetMapping
    public ResponseEntity<List<EmailVoluntario>> getAllEmailVoluntarios() {
        return ResponseEntity.ok(emailVoluntarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailVoluntario> getEmailVoluntarioById(@PathVariable Long id) {
        return emailVoluntarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmailVoluntario> createEmailVoluntario(@RequestBody EmailVoluntario emailVoluntario) {
        return ResponseEntity.ok(emailVoluntarioService.save(emailVoluntario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailVoluntario> updateEmailVoluntario(@PathVariable Long id, @RequestBody EmailVoluntario emailVoluntario) {
        if (!emailVoluntarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        emailVoluntario.setIdEmailVoluntario(id);
        return ResponseEntity.ok(emailVoluntarioService.save(emailVoluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailVoluntario(@PathVariable Long id) {
        if (!emailVoluntarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        emailVoluntarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}