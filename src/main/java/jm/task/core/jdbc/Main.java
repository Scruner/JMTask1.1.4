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
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.cleanUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.removeUserById(1L);
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.getAllUsers();
    }
}
