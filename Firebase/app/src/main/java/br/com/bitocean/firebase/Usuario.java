package br.com.bitocean.firebase;

public class Usuario {
    private String objectId;
    private String nome;
    private String sobrenome;
    private String sexo;
    private int idade;

    public Usuario() {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public Usuario setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public String getSexo() {
        return sexo;
    }

    public Usuario setSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public int getIdade() {
        return idade;
    }

    public Usuario setIdade(int idade) {
        this.idade = idade;
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
                ", sobrenome='" + sobrenome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", idade=" + idade +
                '}';
    }
}
