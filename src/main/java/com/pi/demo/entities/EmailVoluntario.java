package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_emailVoluntario")
public class EmailVoluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmailVoluntario")
    private Long idEmailVoluntario;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public EmailVoluntario() {}

    public EmailVoluntario(Long idEmailVoluntario, String email) {
        this.idEmailVoluntario = idEmailVoluntario;
        this.email = email;
    }

    public Long getIdEmailVoluntario() {
        return idEmailVoluntario;
    }

    public void setIdEmailVoluntario(Long idEmailVoluntario) {
        this.idEmailVoluntario = idEmailVoluntario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}