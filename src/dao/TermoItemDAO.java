package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.TermoItem;

public class TermoItemDAO {
    private Connection connection;

    public TermoItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(TermoItem termoItem) throws SQLException {
        String sql = "INSERT INTO TermoItem (termo_id, condicao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, termoItem.getTermoId());
            stmt.setString(2, termoItem.getCondicao());
            stmt.executeUpdate();
        }
    }
}
