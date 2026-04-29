package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_sexo")
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSexo")
    private Long idSexo;

    @Column(name = "sexo", nullable = false, length = 1)
    private Character sexo;

    public Sexo() {}

    public Sexo(Long idSexo, Character sexo) {
        this.idSexo = idSexo;
        this.sexo = sexo;
    }

    public Long getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Long idSexo) {
        this.idSexo = idSexo;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
}
