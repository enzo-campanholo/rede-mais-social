package controllers;

import java.util.Random;
import dao.CandidatoDAO;
import dao.TokenValidacaoEmailDAO;
import models.Candidato;
import models.TokenValidacaoEmail;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.SQLException;

public class EmailController {
    private CandidatoDAO candidatoDAO;
    private TokenValidacaoEmailDAO tokenDAO;

    public EmailController(CandidatoDAO candidatoDAO, TokenValidacaoEmailDAO tokenDAO) {
        this.candidatoDAO = candidatoDAO;
        this.tokenDAO = tokenDAO;
    }

    public int enviarToken(String email) {
        int token = 0;
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                Random rand = new Random();
                token = rand.nextInt(99999);

                System.out.println("---- EMAIL -------");
                System.out.println("Token: " + token);
                System.out.println("----------------");

                TokenValidacaoEmail tve = new TokenValidacaoEmail(candidato.getId(), token, Timestamp.valueOf(LocalDateTime.now()), false);
                tokenDAO.create(tve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }
}
