package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService us = new UserServiceImpl();

        us.createUsersTable();
        us.saveUser("Ю1", "Ф1", (byte) 1);
        us.saveUser("Ю2", "Ф2", (byte) 2);
        us.saveUser("Ю3", "Ф3", (byte) 3);
        us.saveUser("Ю4", "Ф4", (byte) 4);
        for (User user :
                us.getAllUsers()) {
            System.out.println(user.toString());
        }
        us.cleanUsersTable();
        us.dropUsersTable();

    }


}
