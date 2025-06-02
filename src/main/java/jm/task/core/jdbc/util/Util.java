package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    final String url = "jdbc:mysql://localhost:3306";
    final String user = "admin";
    final String password = "0000";

    public Connection getConnection(){

        Connection conn;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to database");
            conn = connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
