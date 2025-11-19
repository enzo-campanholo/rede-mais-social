package models;

public class Interesse {
    private int id;
    private int perfilId;
    private String nome;
    private String descricao;

    public Interesse() {}

    public Interesse(int perfilId, String nome, String descricao) {
        this.perfilId = perfilId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPerfilId() { return perfilId; }
    public void setPerfilId(int perfilId) { this.perfilId = perfilId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
