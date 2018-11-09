package br.com.bitocean.cardview.model;

public class Post {

    private String id;
    private String usuario;
    private int imagem;
    private String dataPub;
    private String text;

    public Post() {
    }

    public Post(String usuario, int imagem, String text) {
        this.usuario = usuario;
        this.imagem = imagem;
        this.text = text;
    }

    public Post(String id, String usuario, int imagem, String dataPub, String text) {
        this.id = id;
        this.usuario = usuario;
        this.imagem = imagem;
        this.dataPub = dataPub;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public Post setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public Post setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public int getImagem() {
        return imagem;
    }

    public Post setImagem(int imagem) {
        this.imagem = imagem;
        return this;
    }

    public String getDataPub() {
        return dataPub;
    }

    public Post setDataPub(String dataPub) {
        this.dataPub = dataPub;
        return this;
    }

    public String getText() {
        return text;
    }

    public Post setText(String text) {
        this.text = text;
        return this;
    }
}
