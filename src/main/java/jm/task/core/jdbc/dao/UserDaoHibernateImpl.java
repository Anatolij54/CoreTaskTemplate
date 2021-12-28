package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction tx1 = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            String createMyTable = "CREATE TABLE IF NOT EXISTS `users_test` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) DEFAULT NULL,\n" +
                    "  `age` TINYINT DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";

            Query query = session.createSQLQuery(createMyTable).addEntity(User.class);
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка создания Таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx1 = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            String dropMyTable = "DROP TABLE IF EXISTS `users_test`";
            Query query = session.createSQLQuery(dropMyTable).addEntity(User.class);
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка удаления Таблицы");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx1 = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка сохранения USER");
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction tx1 = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка удаления User по id");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction tx1 = null;
        List<User> users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            users = session.createQuery("from User").list();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка получения всех User");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx1 = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users_test").executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            System.out.println("Ошибка очистки таблицы");
        }
    }
}
