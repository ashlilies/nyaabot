package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.DB_URL,
                                           Constants.DB_USERNAME,
                                           Constants.DB_PASSWORD);
    }
}
