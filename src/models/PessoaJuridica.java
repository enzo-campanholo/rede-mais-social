package models;

public class PessoaJuridica {
    private int id;
    private int candidatoId;
    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica() {}

    public PessoaJuridica(int candidatoId, String cnpj, String razaoSocial) {
        this.candidatoId = candidatoId;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatoId() { return candidatoId; }
    public void setCandidatoId(int candidatoId) { this.candidatoId = candidatoId; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
}
