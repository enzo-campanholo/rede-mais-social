package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Candidato extends Model {
  public static void alterar(int id, String coluna, String valor) {
    try {
      PreparedStatement state = connection
          .prepareStatement("UPDATE Candidato SET estadoAfiliacao = ? WHERE id = ?");
      state.setString(1, valor);
      state.setInt(2, id);

      state.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void criar(String email, String senha, String estadoAfiliacao) {
    try {
      PreparedStatement state = connection
          .prepareStatement("INSERT INTO Candidato (email, senha, estadoAfiliacao) VALUES (?, ?, ?)");

      state.setString(1, email);
      state.setString(2, senha);
      state.setString(3, estadoAfiliacao);
      state.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ResultSet busca(String email) {
    try {
      PreparedStatement stat = connection.prepareStatement("SELECT * FROM Candidato WHERE email = ?");
      stat.setString(1, email);

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
