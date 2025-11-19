package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Representante;

public class RepresentanteDAO {
    private Connection connection;

    public RepresentanteDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Representante representante) throws SQLException {
        String sql = "INSERT INTO Representante (pessoa_juridica_id, nome, doc_identificacao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, representante.getPessoaJuridicaId());
            stmt.setString(2, representante.getNome());
            stmt.setString(3, representante.getDocIdentificacao());
            stmt.executeUpdate();
        }
    }
}
