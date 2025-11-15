package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Termo extends Model {

  public static void criar(String versao, String conteudo) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO Termo (versao, conteudo) VALUES (?, ?)");

      stat.setString(1, versao);
      stat.setString(2, conteudo);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ResultSet busca(String versao) {
    try {
      PreparedStatement stat = connection.prepareStatement("SELECT * FROM Termo WHERE versao = ?");
      stat.setString(1, versao);

      ResultSet res = stat.executeQuery();

      if (!res.next())
        return null;

      return res;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
