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
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration conf = new Configuration();

            Properties c = new Properties();

            c.put(Environment.URL, HOST);
            c.put(Environment.USER, USERNAME);
            c.put(Environment.PASS, PASSWORD);
            c.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            c.put(Environment.HBM2DDL_AUTO, "create-drop");
            c.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            c.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            conf.setProperties(c);
            conf.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Connection error\n" + ex);
        }
        return connection;
    }
}





