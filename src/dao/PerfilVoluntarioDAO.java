package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.PerfilVoluntario;

public class PerfilVoluntarioDAO {
    private Connection connection;

    public PerfilVoluntarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PerfilVoluntario perfil) throws SQLException {
        String sql = "INSERT INTO PerfilVoluntario (candidato_id, bibliografia, disponibilidade, preferencias) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, perfil.getCandidatoId());
            stmt.setString(2, perfil.getBibliografia());
            stmt.setString(3, perfil.getDisponibilidade());
            stmt.setString(4, perfil.getPreferencias());
            stmt.executeUpdate();
        }
    }

    public PerfilVoluntario findByCandidatoId(int candidatoId) throws SQLException {
        String sql = "SELECT * FROM PerfilVoluntario WHERE candidato_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PerfilVoluntario perfil = new PerfilVoluntario();
                    perfil.setId(rs.getInt("id"));
                    perfil.setCandidatoId(rs.getInt("candidato_id"));
                    perfil.setBibliografia(rs.getString("bibliografia"));
                    perfil.setDisponibilidade(rs.getString("disponibilidade"));
                    perfil.setPreferencias(rs.getString("preferencias"));
                    return perfil;
                }
            }
        }
        return null;
    }
}
