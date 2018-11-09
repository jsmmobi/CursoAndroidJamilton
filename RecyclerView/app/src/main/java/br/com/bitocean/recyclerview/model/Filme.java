package br.com.bitocean.recyclerview.model;

import java.io.Serializable;

public class Filme implements Serializable {
    private String titulo;
    private String ano;
    private String genero;

    public Filme() {
    }

    public Filme(String titulo, String ano, String genero) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public Filme setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getAno() {
        return ano;
    }

    public Filme setAno(String ano) {
        this.ano = ano;
        return this;
    }

    public String getGenero() {
        return genero;
    }

    public Filme setGenero(String genero) {
        this.genero = genero;
        return this;
    }
}
