package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_especie")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecie")
    private Long idEspecie;

    @Column(name = "especie", nullable = false, length = 45)
    private String especie;

    public Especie() {}

    public Especie(Long idEspecie, String especie) {
        this.idEspecie = idEspecie;
        this.especie = especie;
    }

    public Long getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Long idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
