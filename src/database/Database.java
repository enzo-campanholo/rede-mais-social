package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Database {
    private static Database instance;
    private Connection connection;
    private String url = "jdbc:sqlite:database.db";

    private Database() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void init(String initSqlPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(initSqlPath)));
            Statement statement = connection.createStatement();
            statement.executeUpdate(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
