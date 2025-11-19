package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Candidato;

public class CandidatoDAO {
    private Connection connection;

    public CandidatoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Candidato candidato) throws SQLException {
        String sql = "INSERT INTO Candidato (email, senha, estadoAfiliacao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidato.getEmail());
            stmt.setString(2, candidato.getSenha());
            stmt.setString(3, candidato.getEstadoAfiliacao());
            stmt.executeUpdate();
        }
    }

    public Candidato findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Candidato WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Candidato candidato = new Candidato();
                    candidato.setId(rs.getInt("id"));
                    candidato.setEmail(rs.getString("email"));
                    candidato.setSenha(rs.getString("senha"));
                    candidato.setEstadoAfiliacao(rs.getString("estadoAfiliacao"));
                    return candidato;
                }
            }
        }
        return null;
    }

    public void updateEstadoAfiliacao(int id, String novoEstado) throws SQLException {
        String sql = "UPDATE Candidato SET estadoAfiliacao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoEstado);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}
