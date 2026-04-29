package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_emailMensagem")
public class EmailMensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmailMensagem")
    private Long idEmailMensagem;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public EmailMensagem() {}

    public EmailMensagem(Long idEmailMensagem, String email) {
        this.idEmailMensagem = idEmailMensagem;
        this.email = email;
    }

    public Long getIdEmailMensagem() {
        return idEmailMensagem;
    }

    public void setIdEmailMensagem(Long idEmailMensagem) {
        this.idEmailMensagem = idEmailMensagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
