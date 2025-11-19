package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.PessoaJuridica;

public class PessoaJuridicaDAO {
    private Connection connection;

    public PessoaJuridicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PessoaJuridica pessoaJuridica) throws SQLException {
        String sql = "INSERT INTO PessoaJuridica (candidato_id, cnpj, razao_social) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pessoaJuridica.getCandidatoId());
            stmt.setString(2, pessoaJuridica.getCnpj());
            stmt.setString(3, pessoaJuridica.getRazaoSocial());
            stmt.executeUpdate();
        }
    }

    public PessoaJuridica findByCandidatoId(int candidatoId) throws SQLException {
        String sql = "SELECT * FROM PessoaJuridica WHERE candidato_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PessoaJuridica pj = new PessoaJuridica();
                    pj.setId(rs.getInt("id"));
                    pj.setCandidatoId(rs.getInt("candidato_id"));
                    pj.setCnpj(rs.getString("cnpj"));
                    pj.setRazaoSocial(rs.getString("razao_social"));
                    return pj;
                }
            }
        }
        return null;
    }
}
