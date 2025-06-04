package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection(){

        final String url = "jdbc:mysql://localhost:3306/login_schema";
        final String user = "root";
        final String password = "1111";

        Connection conn;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
