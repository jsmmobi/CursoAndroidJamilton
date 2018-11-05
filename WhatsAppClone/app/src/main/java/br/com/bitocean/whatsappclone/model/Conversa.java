package br.com.bitocean.whatsappclone.model;

import java.io.Serializable;

public class Conversa implements Serializable {
    public static final String NODE="Conversa";
    private String idUsuario;
    private String nome;
    private Mensagem mensagem;

    public Conversa() {
    }

    public Conversa(String idUsuario, String nome, Mensagem mensagem) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public Conversa setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Conversa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public Conversa setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
        return this;
    }
}
