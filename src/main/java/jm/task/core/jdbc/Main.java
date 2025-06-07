package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {



        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Bob","Dylan", (byte) 27);
        userService.saveUser("Jack","Hammer",(byte) 40);
        userService.saveUser("Anna","Frey",(byte) 18);
        userService.saveUser("Victoria","Secret",(byte) 99);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
