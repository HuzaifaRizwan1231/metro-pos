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
import com.metro_pos.Store.UserStore;

public class UserService extends BaseService {

    public User getByUserNameAndPasswordAndRole(String email, String password, String role) {

        String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND role = ?";
        User user;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("employee_num"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getInt("branch_code"), rs.getInt("salary"), rs.getString("role"),
                        rs.getBoolean("is_first_login"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }
        return null;
    }

    public Boolean update(String password) {

        String sql = "UPDATE user SET password = ?, is_first_login = ? WHERE name = ? AND role = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setBoolean(2, false);
            ps.setString(3, UserStore.getCurrentUser().getName());
            ps.setString(4, UserStore.getCurrentUser().getRole());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }
        return false;
    }
}