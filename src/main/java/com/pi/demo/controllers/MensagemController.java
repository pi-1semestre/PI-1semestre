package com.pi.demo.controllers;

import com.pi.demo.entities.Mensagem;
import com.pi.demo.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return mensagemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mensagem> createMensagem(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemService.save(mensagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> updateMensagem(@PathVariable Long id, @RequestBody Mensagem mensagem) {
        if (!mensagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        mensagem.setIdMensagem(id);
        return ResponseEntity.ok(mensagemService.save(mensagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensagem(@PathVariable Long id) {
        if (!mensagemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        mensagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}