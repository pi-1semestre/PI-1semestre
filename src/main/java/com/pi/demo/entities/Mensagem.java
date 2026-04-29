package com.pi.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_mensagem")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagem")
    private Long idMensagem;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "imagemAntes", nullable = false, length = 255)
    private String imagemAntes;

    @Column(name = "imagemDepois", nullable = false, length = 255)
    private String imagemDepois;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_emailMensagem_idEmailMensagem", nullable = false)
    private EmailMensagem emailMensagem;

    public Mensagem() {}

    public Mensagem(Long idMensagem, String descricao, EmailMensagem emailMensagem, String imagemAntes, String imagemDepois, boolean status) {
        this.idMensagem = idMensagem;
        this.descricao = descricao;
        this.emailMensagem = emailMensagem;
        this.imagemAntes = imagemAntes;
        this.imagemDepois = imagemDepois;
        this.status = status;
    }

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EmailMensagem getEmailMensagem() {
        return emailMensagem;
    }

    public void setEmailMensagem(EmailMensagem emailMensagem) {
        this.emailMensagem = emailMensagem;
    }

    public String getImagemAntes() {
        return imagemAntes;
    }

    public void setImagemAntes(String imagemAntes) {
        this.imagemAntes = imagemAntes;
    }

    public String getImagemDepois() {
        return imagemDepois;
    }

    public void setImagemDepois(String imagemDepois) {
        this.imagemDepois = imagemDepois;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}