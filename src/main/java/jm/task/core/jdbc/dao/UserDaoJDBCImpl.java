package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    {
        try {
            connection = Util.getConnection();
        } catch (SQLException throwables) {
            System.err.println("Bad connection to base");
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE UsersTable (ID bigint NOT NULL AUTO_INCREMENT," +
                "Name varchar(255), LastName varchar(255), Age tinyint, PRIMARY KEY (ID))";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException throwables) {
            System.err.println("Table already exist");
        }

    }

    public void dropUsersTable() {
        try {
            connection.createStatement().execute("DROP TABLE UsersTable");
        } catch (SQLException throwables) {
            System.err.println("Table Do not exist");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO UsersTable (Name, LastName, Age) VALUES (?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            System.err.println("Table do not exist");
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM UsersTable WHERE ID=?");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            System.err.println("User by this ID do not exist");
        }

    }

    public List<User> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT Name, LastName, Age FROM UsersTable");
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("Name")
                        , resultSet.getString("LastName"), resultSet.getByte("Age")));
            }
            return userList;

        } catch (SQLException throwables) {
            return null;
        }
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().execute("DELETE FROM UsersTable");
        } catch (SQLException throwables) {
            System.err.println("Delete is not applied");
        }

    }
}
