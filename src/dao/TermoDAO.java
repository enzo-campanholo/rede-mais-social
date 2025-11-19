package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Termo;

public class TermoDAO {
    private Connection connection;

    public TermoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Termo termo) throws SQLException {
        String sql = "INSERT INTO Termo (versao, conteudo) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, termo.getVersao());
            stmt.setString(2, termo.getConteudo());
            stmt.executeUpdate();
        }
    }

    public Termo findByVersao(String versao) throws SQLException {
        String sql = "SELECT * FROM Termo WHERE versao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, versao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Termo termo = new Termo();
                    termo.setId(rs.getInt("id"));
                    termo.setVersao(rs.getString("versao"));
                    termo.setConteudo(rs.getString("conteudo"));
                    return termo;
                }
            }
        }
        return null;
    }
}
