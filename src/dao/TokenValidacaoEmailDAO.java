package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.TokenValidacaoEmail;

public class TokenValidacaoEmailDAO {
    private Connection connection;

    public TokenValidacaoEmailDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(TokenValidacaoEmail token) throws SQLException {
        String sql = "INSERT INTO TokenValidacaoEmail (candidato_id, token, expira_em, usado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, token.getCandidatoId());
            stmt.setInt(2, token.getToken());
            stmt.setTimestamp(3, token.getExpiraEm());
            stmt.setBoolean(4, token.isUsado());
            stmt.executeUpdate();
        }
    }
}
