package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_porte")
public class Porte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPorte")
    private Long idPorte;

    @Column(name = "porte", nullable = false, length = 45)
    private String porte;

    public Porte() {}

    public Porte(Long idPorte, String porte) {
        this.idPorte = idPorte;
        this.porte = porte;
    }

    public Long getIdPorte() {
        return idPorte;
    }

    public void setIdPorte(Long idPorte) {
        this.idPorte = idPorte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
