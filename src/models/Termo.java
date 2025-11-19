package models;

public class Termo {
    private int id;
    private String versao;
    private String conteudo;

    public Termo() {}

    public Termo(String versao, String conteudo) {
        this.versao = versao;
        this.conteudo = conteudo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
}
