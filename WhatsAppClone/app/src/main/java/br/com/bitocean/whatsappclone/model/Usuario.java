package br.com.bitocean.whatsappclone.model;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

import br.com.bitocean.whatsappclone.config.FirebaseConfig;

public class Usuario implements Serializable {
    public static final String NODE="Usuario";

    public static final String PROP_OBJECT_ID="objectId";
    public static final String PROP_NOME="nome";
    public static final String PROP_EMAIL="email";
    public static final String PROP_SENHA="senha";
    public static final String PROP_FOTO="foto";
    public static final String PROP_PROVIDER_ID="providerId";

    private String objectId;
    private String nome;
    private String email;

    private String senha;
    private String foto;
    private String providerId;
    private Long dataCadastro;

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public void salvar(Context context){
        DatabaseReference reference = FirebaseConfig.getReference();
        reference.child(NODE).child(objectId).setValue(this);
    }

    public String getObjectId() {
        return objectId;
    }

    public Usuario setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public Usuario setFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public String getProviderId() {
        return providerId;
    }

    public Usuario setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return objectId != null ? objectId.equals(usuario.objectId) : usuario.objectId == null;
    }

    @Override
    public int hashCode() {
        return objectId != null ? objectId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "objectId='" + objectId + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Contato usuarioToContato(){
        return new Contato(this.objectId,this.getNome(),this.getEmail(),this.getFoto());
    }
}
