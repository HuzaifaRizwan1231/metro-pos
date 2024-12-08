package com.metro_pos.Service;

import java.sql.Connection;

import com.metro_pos.Database.DatabaseConnection;

public class BaseService {
    protected Connection conn;

    public BaseService() {
        this.conn = DatabaseConnection.getConnection();
    }
}
