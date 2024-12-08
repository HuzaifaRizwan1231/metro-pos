package com.metro_pos.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.metro_pos.Database.DatabaseConnection;

public class ManagerController {

    public boolean addManager(String name, String email, Integer branchCode, Double salary,String role) {
        // Adjusted SQL query (removed employee_num field)
        String sql = "INSERT INTO user (name, email, password, branch_code, salary, role, is_first_login) "
                   + "VALUES (?, ?, ?, ?, ?, ?, TRUE)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set parameters (removed employee_num)
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashPassword("123")); // Assume "123" is a default password to be hashed
            ps.setInt(4, branchCode);
            ps.setDouble(5, salary);
            ps.setString(6, role);
            // Execute the statement
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String password) {
        // Implement password hashing here, e.g., using BCrypt
        return password; // Replace with actual hashing logic
    }
}
