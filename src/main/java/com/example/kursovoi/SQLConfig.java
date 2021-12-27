package com.example.kursovoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConfig {
    private String url = "jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_72c19c371194bdb?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "b7a6f89901f0bd";
    private String password = "77a196b7";

    private Connection connection;

    public SQLConfig() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
