package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    JDBC connect = new JDBC();

    public void createUsersTable() {
        String createMyTable = "CREATE TABLE IF NOT EXISTS `users_test` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) DEFAULT NULL,\n" +
                "  `age` TINYINT DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
        try {
            connect.getStatement().executeUpdate(createMyTable);
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropMyTable = "DROP TABLE IF EXISTS `users_test`";
        try {
            connect.getStatement().executeUpdate(dropMyTable);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users_test (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
        try {
            connect.getStatement().executeUpdate(save);
        } catch (SQLException e) {
            System.out.println("Ошибка добавления User");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeid = "DELETE FROM users_test WHERE id=" + id + ";";
        try {
            connect.getStatement().executeUpdate(removeid);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления User по id");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUS = "SELECT *FROM users_test";
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement stat = connect.getConnection().prepareStatement(getAllUS);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех User");
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String remove = "DELETE FROM users_test;";
        try {
            connect.getStatement().executeUpdate(remove);
        } catch (SQLException e) {
            System.out.println("Ошибка очистки User таблицы");
            e.printStackTrace();
        }
    }
}
