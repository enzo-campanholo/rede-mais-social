package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Model {
  public static Connection connection;
  public static String init_sql;

  public static void init(String url, String init_sql) {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:database.db");
      buildDatabase(init_sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected static void buildDatabase(String path) {
    try {
      String content = new String(Files.readAllBytes(Paths.get(path)));
      set(content);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected static ResultSet get(String query) {

    ResultSet resultSet = null;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  protected static void set(String query) {
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
