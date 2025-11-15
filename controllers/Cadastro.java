package controllers;

import models.Candidato;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Representante;
import models.Termo;
import models.Pedido;
import models.Endereco;
import models.PerfilVoluntario;
import models.Habilidade;
import models.Interesse;

import java.sql.ResultSet;
import java.sql.Date;

public class Cadastro {

  public static int verificarExistencia(String email) {
    ResultSet result = Candidato.busca(email);

    if (result == null)
      return 0;

    return -1;
  }

  public static int armazenarPessoaFisica(String email, String cpf, String nome, String sexo, Date data_nascimento,
      String nacionalidade, String profissao) {

    Candidato.criar(email, "password", "em_progresso");

    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      PessoaFisica.criar(candidato_id, nome, cpf, sexo, data_nascimento, nacionalidade, profissao);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarPessoaJuridica(String email, String cnpj, String razao_social) {

    Candidato.criar(email, "password", "em_progresso");

    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      PessoaJuridica.criar(candidato_id, cnpj, razao_social);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarRepresentante(String nome, String email, String doc_identificacao) {
    try {
      int pessoa_juridica_id = PessoaJuridica.busca(email).getInt("id");
      Representante.criar(pessoa_juridica_id, nome, doc_identificacao);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarEndereco(String email, String logradouro, int numero, String complemento,
      String bairro, String cidade,
      String uf, String cep) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      Endereco.criar(candidato_id, logradouro, numero, complemento, bairro, cidade, uf, cep);

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarPerfil(String email, String bibliografia, String disponibilidade, String preferencias) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      PerfilVoluntario.criar(candidato_id, bibliografia, disponibilidade, preferencias);

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarHabilidade(String email, String nome, String descricao) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      int perfil_id = PerfilVoluntario.buscar(candidato_id).getInt("id");
      Habilidade.criar(perfil_id, nome, descricao);

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static int armazenarInteresse(String email, String nome, String descricao) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      int perfil_id = PerfilVoluntario.buscar(candidato_id).getInt("id");

      Interesse.criar(perfil_id, nome, descricao);

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return 0;
  }

  public static String buscarTermo() {
    try {
      String conteudo = Termo.busca("1.0").getString("conteudo");
      return conteudo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void cadastrarTermo(String email, boolean aceito) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      int termo_id = Termo.busca("1.0").getInt("id");
      Date date = Date.valueOf("2000-10-10");

      Pedido.criar(candidato_id, termo_id, date, aceito);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void mudarStatus(String email, String status) {
    try {
      int candidato_id = Candidato.busca(email).getInt("id");
      Candidato.alterar(candidato_id, "estadoAfiliacao", status);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
