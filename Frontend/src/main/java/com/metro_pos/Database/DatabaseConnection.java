package com.metro_pos.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn;
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir")
            + "\\Frontend\\src\\main\\java\\com\\metro_pos\\Database\\database.sqlite";

    public static Connection getConnection() {
        try {
            if (conn != null) {
                return conn;
            }
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
