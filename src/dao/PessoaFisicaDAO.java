package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.PessoaFisica;

public class PessoaFisicaDAO {
    private Connection connection;

    public PessoaFisicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PessoaFisica pessoaFisica) throws SQLException {
        String sql = "INSERT INTO PessoaFisica (candidato_id, nome, cpf, sexo, data_nascimento, nacionalidade, profissao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pessoaFisica.getCandidatoId());
            stmt.setString(2, pessoaFisica.getNome());
            stmt.setString(3, pessoaFisica.getCpf());
            stmt.setString(4, pessoaFisica.getSexo());
            stmt.setDate(5, pessoaFisica.getDataNascimento());
            stmt.setString(6, pessoaFisica.getNacionalidade());
            stmt.setString(7, pessoaFisica.getProfissao());
            stmt.executeUpdate();
        }
    }
}
