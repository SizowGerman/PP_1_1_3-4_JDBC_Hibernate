package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    SessionFactory sessionFactory = Util.getSessionFactory();
    Transaction transaction = null;

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "age INT)";

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users";

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);

            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return users;

    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE Users";

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }
}
