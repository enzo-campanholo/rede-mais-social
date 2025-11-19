package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Interesse;

public class InteresseDAO {
    private Connection connection;

    public InteresseDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Interesse interesse) throws SQLException {
        String sql = "INSERT INTO Interesse (perfil_id, nome, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, interesse.getPerfilId());
            stmt.setString(2, interesse.getNome());
            stmt.setString(3, interesse.getDescricao());
            stmt.executeUpdate();
        }
    }
}
