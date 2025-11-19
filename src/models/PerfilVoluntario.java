package models;

public class PerfilVoluntario {
    private int id;
    private int candidatoId;
    private String bibliografia;
    private String disponibilidade;
    private String preferencias;

    public PerfilVoluntario() {}

    public PerfilVoluntario(int candidatoId, String bibliografia, String disponibilidade, String preferencias) {
        this.candidatoId = candidatoId;
        this.bibliografia = bibliografia;
        this.disponibilidade = disponibilidade;
        this.preferencias = preferencias;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatoId() { return candidatoId; }
    public void setCandidatoId(int candidatoId) { this.candidatoId = candidatoId; }

    public String getBibliografia() { return bibliografia; }
    public void setBibliografia(String bibliografia) { this.bibliografia = bibliografia; }

    public String getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(String disponibilidade) { this.disponibilidade = disponibilidade; }

    public String getPreferencias() { return preferencias; }
    public void setPreferencias(String preferencias) { this.preferencias = preferencias; }
}
