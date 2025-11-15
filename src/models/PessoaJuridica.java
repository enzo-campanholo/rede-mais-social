package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PessoaJuridica extends Model {
  public static ResultSet busca(String email) {
    try {
      PreparedStatement candidato_stat = connection.prepareStatement("SELECT * FROM Candidato WHERE email = ?");

      candidato_stat.setString(1, email);

      ResultSet candidato_res = candidato_stat.executeQuery();

      candidato_res.next();
      int candidato_id = candidato_res.getInt("id");

      PreparedStatement stat = connection.prepareStatement("SELECT * FROM PessoaJuridica WHERE candidato_id = ?");

      stat.setInt(1, candidato_id);

      ResultSet res = stat.executeQuery();

      if (!res.next())
        return null;

      return res;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void criar(int candidato_id, String cnpj, String razao_social) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO PessoaJuridica (candidato_id, cnpj, razao_social) VALUES (?, ?, ?)");

      stat.setInt(1, candidato_id);
      stat.setString(2, cnpj);
      stat.setString(3, razao_social);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
