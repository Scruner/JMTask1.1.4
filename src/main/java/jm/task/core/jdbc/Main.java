package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.saveUser("Stepan", "Stepanov", (byte) 32);
        userService.saveUser("Petr", "Petrov", (byte) 40);
        userService.saveUser("Jon", "BonJovi", (byte) 55);
        System.out.println(userService.getAllUsers());
        userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
