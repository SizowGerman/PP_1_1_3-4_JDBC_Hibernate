package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "age INT)";

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users";

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            User user = new User(name, lastName, age);

            session.save(user);
            session.getTransaction().commit();

            System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");

        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            User user = (User) session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();

        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();

        }

        return users;

    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE Users";

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();

        }

    }
}
