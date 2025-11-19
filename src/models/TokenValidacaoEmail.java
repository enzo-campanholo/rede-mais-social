package models;

import java.sql.Timestamp;

public class TokenValidacaoEmail {
    private int id;
    private int candidatoId;
    private int token;
    private Timestamp expiraEm;
    private boolean usado;

    public TokenValidacaoEmail() {}

    public TokenValidacaoEmail(int candidatoId, int token, Timestamp expiraEm, boolean usado) {
        this.candidatoId = candidatoId;
        this.token = token;
        this.expiraEm = expiraEm;
        this.usado = usado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatoId() { return candidatoId; }
    public void setCandidatoId(int candidatoId) { this.candidatoId = candidatoId; }

    public int getToken() { return token; }
    public void setToken(int token) { this.token = token; }

    public Timestamp getExpiraEm() { return expiraEm; }
    public void setExpiraEm(Timestamp expiraEm) { this.expiraEm = expiraEm; }

    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}
