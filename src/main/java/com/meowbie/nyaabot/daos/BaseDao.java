package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.DB_URL,
                                           Constants.DB_USERNAME,
                                           Constants.DB_PASSWORD);
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
