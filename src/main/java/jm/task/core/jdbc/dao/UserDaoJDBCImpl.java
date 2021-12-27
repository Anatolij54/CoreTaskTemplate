package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.Util.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    JDBC connect = new Util.JDBC();

    public void createUsersTable() {
        String createMyTable = "CREATE TABLE IF NOT EXISTS `users_test` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) DEFAULT NULL,\n" +
                "  `age` TINYINT DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
        Savepoint savepointOne = null;
        try {
            savepointOne = connect.getConnection().setSavepoint("SavepointOne");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {

            connect.getConnection().createStatement().executeUpdate(createMyTable);
            connect.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();
            try {
                connect.getConnection().rollback(savepointOne);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void dropUsersTable() {
        String dropMyTable = "DROP TABLE IF EXISTS `users_test`";
        try {
            connect.getConnection().createStatement().executeUpdate(dropMyTable);
            connect.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users_test (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
        try {
            connect.getConnection().createStatement().executeUpdate(save);
            connect.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления User");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeid = "DELETE FROM users_test WHERE id=" + id + ";";
        try {
            connect.getConnection().createStatement().executeUpdate(removeid);
            connect.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления User по id");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUS = "SELECT *FROM users_test";
        List<User> list = new ArrayList<>();
        Savepoint savepointTwo = null;
        try {
            savepointTwo = connect.getConnection().setSavepoint("SavepointTwo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet stat = connect.getConnection().createStatement().executeQuery(getAllUS);
            connect.getConnection().commit();
            User user = null;
            while (stat.next()) {
                user = new User(stat.getString("name"), stat.getString("lastName"),
                        stat.getByte("age"));
                user.setId(stat.getLong("id"));
                list.add(user);
            }
            if(user != null) {list.add(user);}
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех User");
            e.printStackTrace();
            try {
                connect.getConnection().rollback(savepointTwo);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        String remove = "DELETE FROM users_test";
        try {
            connect.getConnection().createStatement().execute(remove);
            connect.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Ошибка очистки User таблицы");
            e.printStackTrace();
        }
    }
}
