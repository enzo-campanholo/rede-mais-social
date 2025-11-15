package models;

import java.sql.PreparedStatement;
import java.sql.Date;

public class Pedido extends Model {

  public static void criar(int candidato_id, int termo_id, Date data_aceite, boolean aceito) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO Pedido (candidato_id, termo_id, data_aceite, aceito) VALUES (?,?,?,?)");

      stat.setInt(1, candidato_id);
      stat.setInt(2, termo_id);
      stat.setDate(3, data_aceite);
      stat.setBoolean(4, aceito);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
