package com.gladkih.geekbrains.cloudfiles.server.db;

import com.gladkih.geekbrains.cloudfiles.server.mvc.model.User;

import java.io.Closeable;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper implements AutoCloseable {
    private static DBHelper instance;

    private static final String URL = "jdbc:sqlite:" +
            System.getProperty("user.dir") + File.separator +
            "mybase.sqlite";

    private final Connection connection;


    private static String FIELD_LOGIN = "login";
    private static String FIELD_PASS = "pass";

    PreparedStatement pstGetUser;
    PreparedStatement pstAddUser;
    private PreparedStatement pstUpdateUser;
    private PreparedStatement pstDellUser;
    private PreparedStatement pstDellAllUser;
    private PreparedStatement pstGetAllUsers;


    public static synchronized DBHelper getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    private DBHelper() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement();
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'users' ('login' text PRIMARY KEY, 'pass' INTEGER);");

        initPrepareStatement();

    }

    private void initPrepareStatement() throws SQLException {

        pstGetAllUsers = connection.prepareStatement(
                "SELECT * FROM users");

        pstGetUser = connection.prepareStatement(
                "SELECT * FROM users WHERE login = ?");

        pstAddUser = connection.prepareStatement(
                "INSERT INTO users(login, pass) VALUES(?,?);");

        pstUpdateUser = connection.prepareStatement(
                "UPDATE users SET pass = ? WHERE login = ?;");

        pstDellUser = connection.prepareStatement(
                "DELETE FROM users WHERE login = ?;");

        pstDellAllUser = connection.prepareStatement(
                "DELETE FROM users;");
    }


    public List<User> getAllUser() throws SQLException {

        List<User> users = new ArrayList<>();

        ResultSet result = pstGetAllUsers.executeQuery();

        while (result.next()) {
            users.add(new User(result.getString(FIELD_LOGIN), result.getInt(FIELD_PASS)));
        }

        return users;
    }

    public User getUser(String login) throws SQLException {
        pstGetUser.setString(1, login);
        ResultSet result = pstGetUser.executeQuery();

        while (result.next()) {
            return new User(result.getString(FIELD_LOGIN), result.getInt(FIELD_PASS));
        }

        return null;
    }


    public void addUser(User user) throws SQLException {
        pstAddUser.setString(1, user.getLogin());
        pstAddUser.setInt(2, user.getPassHashCode());
        pstAddUser.execute();
    }

    public void updateUser(User user) throws SQLException {
        pstUpdateUser.setInt(1, user.getPassHashCode());
        pstUpdateUser.setString(2, user.getLogin());

        pstUpdateUser.execute();
    }

    public void dellUser(String login) throws SQLException {
        pstDellUser.setString(1, login);
        pstDellUser.execute();
    }

    public void dellAllUser() throws SQLException {
        pstDellAllUser.execute();
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
