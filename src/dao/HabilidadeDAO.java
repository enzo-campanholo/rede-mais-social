package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Habilidade;

public class HabilidadeDAO {
    private Connection connection;

    public HabilidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Habilidade habilidade) throws SQLException {
        String sql = "INSERT INTO Habilidade (perfil_id, nome, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, habilidade.getPerfilId());
            stmt.setString(2, habilidade.getNome());
            stmt.setString(3, habilidade.getDescricao());
            stmt.executeUpdate();
        }
    }
}
