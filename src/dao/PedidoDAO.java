package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Pedido;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (candidato_id, termo_id, data_aceite, aceito) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getCandidatoId());
            stmt.setInt(2, pedido.getTermoId());
            stmt.setDate(3, pedido.getDataAceite());
            stmt.setBoolean(4, pedido.isAceito());
            stmt.executeUpdate();
        }
    }
}
