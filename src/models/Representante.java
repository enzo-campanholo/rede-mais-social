package models;

import java.sql.PreparedStatement;

public class Representante extends Model {
  public static void criar(int pessoa_juridica_id, String nome, String doc_identificacao) {
    try {
      PreparedStatement stat = connection.prepareStatement(
          "INSERT INTO Representante (pessoa_juridica_id, nome, doc_identificacao) VALUES (?, ?, ?)");

      stat.setInt(1, pessoa_juridica_id);
      stat.setString(2, nome);
      stat.setString(3, doc_identificacao);

      stat.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
