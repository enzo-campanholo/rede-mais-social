package models;

public class Representante {
    private int id;
    private int pessoaJuridicaId;
    private String nome;
    private String docIdentificacao;

    public Representante() {}

    public Representante(int pessoaJuridicaId, String nome, String docIdentificacao) {
        this.pessoaJuridicaId = pessoaJuridicaId;
        this.nome = nome;
        this.docIdentificacao = docIdentificacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPessoaJuridicaId() { return pessoaJuridicaId; }
    public void setPessoaJuridicaId(int pessoaJuridicaId) { this.pessoaJuridicaId = pessoaJuridicaId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocIdentificacao() { return docIdentificacao; }
    public void setDocIdentificacao(String docIdentificacao) { this.docIdentificacao = docIdentificacao; }
}
