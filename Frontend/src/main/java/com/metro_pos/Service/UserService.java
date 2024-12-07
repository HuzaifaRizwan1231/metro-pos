package com.metro_pos.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;
import com.metro_pos.Model.User;

public class UserService {

    Connection conn;

    public UserService() {
        this.conn = DatabaseConnection.getConnection();
    }

    public User getByUserName(String userName) {

        String sql = "SELECT * FROM user WHERE name = ?";
        User user;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            int size = 0;
            while (rs.next()) {
                size++;
            }
            System.out.println(size);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }
        return null;
    }
}