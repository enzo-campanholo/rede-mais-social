package models;

import java.sql.Date;

public class Pedido {
    private int id;
    private int candidatoId;
    private int termoId;
    private Date dataAceite;
    private boolean aceito;

    public Pedido() {}

    public Pedido(int candidatoId, int termoId, Date dataAceite, boolean aceito) {
        this.candidatoId = candidatoId;
        this.termoId = termoId;
        this.dataAceite = dataAceite;
        this.aceito = aceito;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatoId() { return candidatoId; }
    public void setCandidatoId(int candidatoId) { this.candidatoId = candidatoId; }

    public int getTermoId() { return termoId; }
    public void setTermoId(int termoId) { this.termoId = termoId; }

    public Date getDataAceite() { return dataAceite; }
    public void setDataAceite(Date dataAceite) { this.dataAceite = dataAceite; }

    public boolean isAceito() { return aceito; }
    public void setAceito(boolean aceito) { this.aceito = aceito; }
}
