package models;

import java.sql.PreparedStatement;

public class Interesse extends Model {

  public static void criar(int perfil_id, String nome, String descricao) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO Interesse (perfil_id, nome, descricao) VALUES (?, ?, ?)");

      stat.setInt(1, perfil_id);
      stat.setString(2, nome);
      stat.setString(3, descricao);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
