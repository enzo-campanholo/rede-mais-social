package models;

import java.sql.PreparedStatement;

public class Endereco extends Model {

  public static void criar(int candidato_id, String logradouro, int numero, String complemento,
      String bairro, String cidade,
      String uf, String cep) {

    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO Endereco (candidato_id, logradouro, numero, complemento, bairro, cidade, uf, cep) VALUES (?,?,?,?,?,?,?,?)");

      stat.setInt(1, candidato_id);
      stat.setString(2, logradouro);
      stat.setInt(3, numero);
      stat.setString(4, complemento);
      stat.setString(5, bairro);
      stat.setString(6, cidade);
      stat.setString(7, uf);
      stat.setString(8, cep);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
