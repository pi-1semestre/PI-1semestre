package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_voluntarios")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVoluntario")
    private Long idVoluntario;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_emailVoluntario_idEmailVoluntario", nullable = false)
    private EmailVoluntario emailVoluntario;

    public Voluntario() {}

    public Voluntario(Long idVoluntario, String nome, Integer idade, EmailVoluntario emailVoluntario) {
        this.idVoluntario = idVoluntario;
        this.nome = nome;
        this.idade = idade;
        this.emailVoluntario = emailVoluntario;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public EmailVoluntario getEmailVoluntario() {
        return emailVoluntario;
    }

    public void setEmailVoluntario(EmailVoluntario emailVoluntario) {
        this.emailVoluntario = emailVoluntario;
    }
}