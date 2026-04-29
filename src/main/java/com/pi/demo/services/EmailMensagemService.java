package com.pi.demo.services;

import com.pi.demo.entities.EmailMensagem;
import com.pi.demo.repositories.EmailMensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailMensagemService {

    
    @Autowired
    private EmailMensagemRepository emailMensagemRepository;

    public List<EmailMensagem> findAll() {
        return emailMensagemRepository.findAll();
    }

    public Optional<EmailMensagem> findById(Long id) {
        return emailMensagemRepository.findById(id);
    }

    public EmailMensagem save(EmailMensagem emailMensagem) {
        return emailMensagemRepository.save(emailMensagem);
    }

    public void deleteById(Long id) {
        emailMensagemRepository.deleteById(id);
    }
}