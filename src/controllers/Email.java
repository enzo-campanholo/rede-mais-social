package controllers;

import java.util.Random;
import models.TokenValidacaoEmail;
import models.Candidato;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Email {

  public static int enviarToken(String email) {
    int token = 0;
    try {

      int candidato_id = Candidato.busca(email).getInt("id");
      Random rand = new Random();
      token = rand.nextInt(99999);

      System.out.println("---- EMAIL -------");
      System.out.println("Token: " + token);
      System.out.println("----------------");

      TokenValidacaoEmail.criar(candidato_id, token, Timestamp.valueOf(LocalDateTime.now()), false);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return token;

  }
}
