package models;

public class Candidato {
    private int id;
    private String email;
    private String senha;
    private String estadoAfiliacao;

    public Candidato() {}

    public Candidato(String email, String senha, String estadoAfiliacao) {
        this.email = email;
        this.senha = senha;
        this.estadoAfiliacao = estadoAfiliacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstadoAfiliacao() {
        return estadoAfiliacao;
    }

    public void setEstadoAfiliacao(String estadoAfiliacao) {
        this.estadoAfiliacao = estadoAfiliacao;
    }
}
