package com.pi.demo.controllers;

import com.pi.demo.entities.EmailMensagem;
import com.pi.demo.services.EmailMensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailMensagemController {

    @Autowired
    private EmailMensagemService emailMensagemService;
    

    @GetMapping
    public ResponseEntity<List<EmailMensagem>> getAllEmails() {
        return ResponseEntity.ok(emailMensagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailMensagem> getEmailById(@PathVariable Long id) {
        return emailMensagemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmailMensagem> createEmail(@RequestBody EmailMensagem emailMensagem) {
        return ResponseEntity.ok(emailMensagemService.save(emailMensagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailMensagem> updateEmail(@PathVariable Long id, @RequestBody EmailMensagem emailMensagem) {
        if (!emailMensagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        emailMensagem.setIdEmailMensagem(id);
        return ResponseEntity.ok(emailMensagemService.save(emailMensagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        if (!emailMensagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        emailMensagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}