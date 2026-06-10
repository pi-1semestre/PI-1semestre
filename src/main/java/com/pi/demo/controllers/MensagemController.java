package com.pi.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.demo.entities.Mensagem;
import com.pi.demo.services.MensagemService;

@RestController
@RequestMapping("/api/v1/mensagens")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping
    public ResponseEntity<List<Mensagem>> getAllMensagens() {
        return ResponseEntity.ok(mensagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> getMensagemById(@PathVariable Long id) {
        Mensagem mensagem = mensagemService.findById(id);
        if (mensagem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mensagem);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Mensagem>> getMensagensByStatus(@PathVariable boolean status) {
        return ResponseEntity.ok(mensagemService.findByStatus(status));
    }
    

    @PostMapping
    public ResponseEntity<Mensagem> createMensagem(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemService.save(mensagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> updateMensagem(@PathVariable Long id, @RequestBody Mensagem mensagem) {
        if (mensagemService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        mensagem.setIdMensagem(id);
        return ResponseEntity.ok(mensagemService.save(mensagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensagem(@PathVariable Long id) {
        if (mensagemService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        mensagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Mensagem>> searchAnimalsByNome(@RequestParam String nome) {
        return ResponseEntity.ok(mensagemService.findByNomeContaining(nome));
    }

    @GetMapping("/animal/{nomeAnimal}")
    public ResponseEntity<List<Mensagem>> searchByNomeAnimal(@PathVariable String nomeAnimal) {
        return ResponseEntity.ok(mensagemService.findByNomeAnimal(nomeAnimal));
    }
}