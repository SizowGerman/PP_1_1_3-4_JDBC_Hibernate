package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Connection connection = Util.getConnection();

    public void createUsersTable() {

        PreparedStatement preparedStatement = null;

        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "age INT)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println("Error creating table");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    System.out.println("Error closing prepared statement after table creation");
                }
            }
        }
    }

    public void dropUsersTable() {

        PreparedStatement preparedStatement = null;

        String sql = "DROP TABLE IF EXISTS Users";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Table dropped");
        } catch (SQLException e) {
            System.out.println("Error dropping table");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    System.out.println("Error closing prepared statement after table dropping");
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Users (name, last_name, age) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
        } catch (SQLException e) {
            System.out.println("Error inserting user");
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement after user insertion");
                }
            }
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
