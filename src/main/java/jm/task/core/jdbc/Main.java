package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
        import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
        import jm.task.core.jdbc.service.UserService;
        import jm.task.core.jdbc.service.UserServiceImpl;
        import jm.task.core.jdbc.util.Util;

        import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.saveUser("Stepan", "Stepanov", (byte) 32);
        userService.saveUser("Petr", "Petrov", (byte) 40);
        userService.saveUser("Jon", "BonJovi", (byte) 55);
        System.out.println(userService.getAllUsers());
        //userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
