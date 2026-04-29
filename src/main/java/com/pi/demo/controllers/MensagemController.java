package com.pi.demo.controllers;

import com.pi.demo.entities.Mensagem;
import com.pi.demo.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

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
        return ResponseEntity.ok(mensagemService.findByStatusTrue());
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
}