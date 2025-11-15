package models;

import java.sql.PreparedStatement;
import java.sql.Date;

public class PessoaFisica extends Model {
  public static void criar(int candidato_id, String nome, String cpf, String sexo, Date data_nascimento,
      String nacionalidade,
      String profissao) {

    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO PessoaFisica (candidato_id, nome, cpf, sexo, data_nascimento, nacionalidade, profissao) VALUES (?, ?, ?, ?, ?, ?, ?)");

      stat.setInt(1, candidato_id);
      stat.setString(2, nome);
      stat.setString(3, cpf);
      stat.setString(4, sexo);
      stat.setDate(5, data_nascimento);
      stat.setString(6, nacionalidade);
      stat.setString(7, profissao);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
