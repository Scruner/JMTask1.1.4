package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Enumeration;

public class Util {

    public static Connection util() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=" +
                "root&useLegacyDatetimeCode=false&serverTimezone=UTC");
    }
}

       /* try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate("CREATE DATABASE db");
            statement.executeUpdate("USE db");
            //statement.executeUpdate("CREATE TABLE persons (name varchar (32), age int (3))");
            //statement.executeUpdate("INSERT INTO persons (name, age) VALUES ('Name117', 17)");
            ResultSet resultSet = statement.executeQuery("select * from persons where age=32 order by name");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " - " +
                        resultSet.getString("age"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
