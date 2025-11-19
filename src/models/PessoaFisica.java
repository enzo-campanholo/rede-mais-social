package models;

import java.sql.Date;

public class PessoaFisica {
    private int id;
    private int candidatoId;
    private String nome;
    private String cpf;
    private String sexo;
    private Date dataNascimento;
    private String nacionalidade;
    private String profissao;

    public PessoaFisica() {}

    public PessoaFisica(int candidatoId, String nome, String cpf, String sexo, Date dataNascimento, String nacionalidade, String profissao) {
        this.candidatoId = candidatoId;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.profissao = profissao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatoId() { return candidatoId; }
    public void setCandidatoId(int candidatoId) { this.candidatoId = candidatoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }
}
