package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Statement statement = Util.getConnection().createStatement();
    private Connection connection;

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
        connection  = Util.getConnection();
    }

    public void createUsersTable() throws SQLException {
        try {
            statement.executeUpdate("CREATE TABLE users (id int, name varchar (64), lastname varchar (64), " +
                    "age int (3))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Таблица не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users " +
                    "(id, name, lastname, age) VALUE (?, ?, ?, ?)");
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.setLong(1, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
