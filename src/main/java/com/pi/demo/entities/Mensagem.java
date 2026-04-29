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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_emailMensagem_idEmailMensagem", nullable = false)
    private EmailMensagem emailMensagem;

    public Mensagem() {}

    public Mensagem(Long idMensagem, String descricao, EmailMensagem emailMensagem) {
        this.idMensagem = idMensagem;
        this.descricao = descricao;
        this.emailMensagem = emailMensagem;
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
}