package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
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
            preparedStatement.execute();
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

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM Users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            System.out.println("Error removing user");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement after user removal");
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
                System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {

        PreparedStatement preparedStatement = null;

        String sql = "TRUNCATE TABLE Users";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            System.out.println("Error cleaning table");
            e.printStackTrace();
        }
    }
}
