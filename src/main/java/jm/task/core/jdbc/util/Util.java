package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root1";

    public static class JDBC {

        private Connection connection = null;


        public JDBC() {
            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
                connection.setAutoCommit(false);
                if (!connection.isClosed()) {
                }
            } catch (SQLException e) {
                System.out.println("Драйвер не зарегистрировался");
            }
        }

        public Connection getConnection() {
            return connection;
        }

    }
}



