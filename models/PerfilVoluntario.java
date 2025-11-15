package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerfilVoluntario extends Model {

  public static ResultSet buscar(int candidato_id) {

    try {
      PreparedStatement stat = connection.prepareStatement("SELECT * FROM PerfilVoluntario WHERE candidato_id = ?");
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

  public static void criar(int candidato_id, String bibliografia,
      String disponiblidade,
      String preferencias) {

    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO PerfilVoluntario (candidato_id, bibliografia, disponibilidade, preferencias) VALUES (?, ?, ?, ?)");

      stat.setInt(1, candidato_id);
      stat.setString(2, bibliografia);
      stat.setString(3, disponiblidade);
      stat.setString(4, preferencias);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
