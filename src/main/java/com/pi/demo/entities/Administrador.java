package com.pi.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdministrador")
    private Long idAdministrador;

    @Column(name = "emailAdm", nullable = false, length = 255)
    private String emailAdm;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;


    public Administrador() {}

    public Administrador(Long idAdministrador, String emailAdm, String senha) {
        this.idAdministrador = idAdministrador;
        this.emailAdm = emailAdm;
        this.senha = senha;
    }

    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
