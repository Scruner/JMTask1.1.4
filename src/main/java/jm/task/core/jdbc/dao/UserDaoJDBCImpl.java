package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.jws.soap.SOAPBinding;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Statement statement = Util.util().createStatement();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() throws SQLException {
        try {
            statement.executeUpdate("CREATE TABLE users (id int, name varchar (64), lastname varchar (64), " +
                    "age int (3))");
        } catch (SQLException e) {
            System.out.println("Таблица создана");
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Таблица удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement preparedStatement = Util.util().prepareStatement("INSERT INTO users " +
                    "(id, name, lastname, age) VALUE (?, ?, ?, ?)");
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.setLong(1, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Чувак принят на работу");
        }
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement preparedStatement = Util.util().prepareStatement("DELETE FROM users (id) VALUE (?)");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Чувак уволен");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                System.out.println("Фся таблица ТУТА, ТУУУТАААА!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Содержимое таблицы удалено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
