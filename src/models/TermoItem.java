package models;

import java.sql.PreparedStatement;

public class TermoItem extends Model {

  public static void criar(int termo_id, String condicao) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO TermoItem (termo_id, condicao) VALUES (?, ?)");

      stat.setInt(1, termo_id);
      stat.setString(2, condicao);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
