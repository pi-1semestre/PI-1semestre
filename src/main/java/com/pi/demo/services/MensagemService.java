package com.pi.demo.services;

import com.pi.demo.entities.Mensagem;
import com.pi.demo.repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MensagemService {

    
    @Autowired
    private MensagemRepository mensagemRepository;
    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    public List<Mensagem> findByStatusTrue() {
        return mensagemRepository.findByStatusTrue();
    }

    public Mensagem findById(Long id) {
        return mensagemRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        mensagemRepository.deleteById(id);
    }

    public Mensagem save(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }
}