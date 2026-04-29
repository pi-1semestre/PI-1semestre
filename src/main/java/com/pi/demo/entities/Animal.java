package com.pi.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnimal")
    private Long idAnimal;

    @Column(name = "nomeAnimal", nullable = false, length = 45)
    private String nomeAnimal;

    @Column(name = "idade", nullable = false, length = 45)
    private String idade;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "imagem", nullable = false, length = 255)
    private String imagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_especie_idEspecie", nullable = false)
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_porte_idPorte", nullable = false)
    private Porte porte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_pelagem_idPelagem", nullable = false)
    private Pelagem pelagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_sexo_idSexo", nullable = false)
    private Sexo sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_administrador_idAdministrador", nullable = false)
    private Administrador administrador;

    public Animal() {}

    public Animal(Long idAnimal, String nomeAnimal, String idade, String imagem, Especie especie, Porte porte, Pelagem pelagem, Sexo sexo, Administrador administrador) {
        this.idAnimal = idAnimal;
        this.nomeAnimal = nomeAnimal;
        this.idade = idade;
        this.imagem = imagem;
        this.especie = especie;
        this.porte = porte;
        this.pelagem = pelagem;
        this.sexo = sexo;
        this.administrador = administrador;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Pelagem getPelagem() {
        return pelagem;
    }

    public void setPelagem(Pelagem pelagem) {
        this.pelagem = pelagem;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
