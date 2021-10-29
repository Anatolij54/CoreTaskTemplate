package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userdaoHIB = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userdaoHIB.createUsersTable();
    }

    public void dropUsersTable() {
        userdaoHIB.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userdaoHIB.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userdaoHIB.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userdaoHIB.getAllUsers();
    }

    public void cleanUsersTable() {
        userdaoHIB.cleanUsersTable();
    }
}
