package models;

public class TermoItem {
    private int id;
    private int termoId;
    private String condicao;

    public TermoItem() {}

    public TermoItem(int termoId, String condicao) {
        this.termoId = termoId;
        this.condicao = condicao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTermoId() { return termoId; }
    public void setTermoId(int termoId) { this.termoId = termoId; }

    public String getCondicao() { return condicao; }
    public void setCondicao(String condicao) { this.condicao = condicao; }
}
