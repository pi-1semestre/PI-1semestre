package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pelagem")
public class Pelagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPelagem")
    private Long idPelagem;

    @Column(name = "pelagem", nullable = false, length = 45)
    private String pelagem;

    public Pelagem() {}

    public Pelagem(Long idPelagem, String pelagem) {
        this.idPelagem = idPelagem;
        this.pelagem = pelagem;
    }

    public Long getIdPelagem() {
        return idPelagem;
    }

    public void setIdPelagem(Long idPelagem) {
        this.idPelagem = idPelagem;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }
}
