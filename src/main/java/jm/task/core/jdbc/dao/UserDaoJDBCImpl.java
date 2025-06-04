package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    UserServiceImpl userService = new UserServiceImpl();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        userService.createUsersTable();
    }

    public void dropUsersTable() {
        userService.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userService.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userService.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void cleanUsersTable() {
        userService.cleanUsersTable();
    }
}
