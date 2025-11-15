package models;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class TokenValidacaoEmail extends Model {

  public static void criar(int candidato_id, int token, Timestamp expira_em, boolean usado) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO TokenValidacaoEmail (candidato_id, token, expira_em, usado) VALUES (?, ?, ?, ?)");

      stat.setInt(1, candidato_id);
      stat.setInt(2, token);
      stat.setTimestamp(3, expira_em);
      stat.setBoolean(4, usado);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
