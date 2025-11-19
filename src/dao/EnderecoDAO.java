package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Endereco;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (candidato_id, logradouro, numero, complemento, bairro, cidade, uf, cep) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getCandidatoId());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getUf());
            stmt.setString(8, endereco.getCep());
            stmt.executeUpdate();
        }
    }
}
