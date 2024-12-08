package com.metro_pos.Controller;
import javax.swing.*;
import com.metro_pos.Controller.ManagerController;
import com.metro_pos.Controller.BranchController;
import java.awt.*;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.metro_pos.Database.DatabaseConnection;

public class ManagerController {

    public boolean addManager(String name, String email, Integer branchCode, Double salary, String role) {
        // SQL query to insert a new manager
        String insertManagerSql = "INSERT INTO user (name, email, password, branch_code, salary, role, is_first_login) "
                + "VALUES (?, ?, ?, ?, ?, ?, TRUE)";
        // SQL query to update manager_assigned in the branch table
        String updateBranchSql = "UPDATE branch SET manager_assigned = TRUE WHERE branch_code = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            // Enable transaction management
            connection.setAutoCommit(false);

            try {

                PreparedStatement insertManagerPs = connection.prepareStatement(insertManagerSql);
                PreparedStatement updateBranchPs = connection.prepareStatement(updateBranchSql);
                // Insert the manager
                insertManagerPs.setString(1, name);
                insertManagerPs.setString(2, email);
                insertManagerPs.setString(3, hashPassword("123")); // Default hashed password
                insertManagerPs.setInt(4, branchCode);
                insertManagerPs.setDouble(5, salary);
                insertManagerPs.setString(6, role);
                int managerRowsAffected = insertManagerPs.executeUpdate();

                // Update the branch table
                updateBranchPs.setInt(1, branchCode);
                int branchRowsAffected = updateBranchPs.executeUpdate();

                // Commit transaction if both operations are successful
                if (managerRowsAffected > 0 && branchRowsAffected > 0) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback(); // Rollback in case of failure
                    return false;
                }
            } catch (Exception ex) {
                
                connection.rollback(); // Rollback in case of exception
                throw ex;
            }
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
