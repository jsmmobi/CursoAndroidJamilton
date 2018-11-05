package br.com.bitocean.whatsappclone.model;

import java.io.Serializable;

public class Contato implements Serializable {
    public static final String NODE="Contato";

    private String objectId;
    private String nome;
    private String email;
    private String foto;

    public Contato() {
    }

    public Contato(String objectId, String nome, String email,String foto) {
        this.objectId = objectId;
        this.nome = nome;
        this.email = email;
        this.foto = foto;
    }

    public String getObjectId() {
        return objectId;
    }

    public Contato setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Contato setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contato setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public Contato setFoto(String foto) {
        this.foto = foto;
        return this;
    }
}
