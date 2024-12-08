package com.metro_pos.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;

public class BranchModel {

    public void insert(String name, String city, String address, String phone) {
        String sql = "INSERT INTO branch (name, city, address, phone) VALUES (?, ?, ?, ?)";
        try {
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, city);
            ps.setString(3, address);
            ps.setString(4, phone);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Branch inserted successfully!");
            } else {
                System.out.println("Failed to insert branch.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting branch: " + e.getMessage());
        }
    }

    public String[][] getAllBranches() {
        String sql = "SELECT * FROM branch"; // Adjust query if needed
        List<String[]> rows = new ArrayList<>();

        try {

            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = new String[8];
                row[0] = String.valueOf(rs.getInt("branch_code")); // Branch Code
                row[1] = rs.getString("name"); // Name
                row[2] = rs.getString("city"); // City
                row[3] = rs.getString("address"); // Address
                row[4] = rs.getString("phone"); // Phone
                row[5] = rs.getString("is_active"); // Active (1 for TRUE, 0 for FALSE)
                row[6] = String.valueOf(rs.getInt("no_of_employees")); // No of Employees
                row[7] = rs.getString("manager_assigned"); // Manager Assigned

                System.out.println(Arrays.toString(row)); // Debugging output
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving branches: " + e.getMessage());
        }

        // Convert the list to a 2D array
        return rows.toArray(new String[0][0]);
    }

    public void updateBranch(int branchCode, String name, String city, String address, boolean isActive, String phone) {
        String sql = "UPDATE branch SET name = ?, city = ?, address = ?, is_active = ?, phone = ? WHERE branch_code = ?";

        try {

            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, city);
            ps.setString(3, address);
            ps.setInt(4, isActive ? 1 : 0);
            ps.setString(5, phone);
            ps.setInt(6, branchCode);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Branch updated successfully!");
            } else {
                System.out.println("No branch found with the given branch code.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while updating branch: " + e.getMessage());
        }
    }

    public List<Integer> getUnassignedBranchCodes() {
        List<Integer> branchCodes = new ArrayList<>();
        String sql = "SELECT branch_code FROM branch WHERE manager_assigned = false AND is_active = true";

        try {

            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                branchCodes.add(rs.getInt("branch_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branchCodes;
    }

    public boolean deleteBranch(int branchCode) {
        String sql = "DELETE FROM branch WHERE branch_code = ?"; // Corrected table name to match your schema

        try {
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, branchCode);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting branch: " + e.getMessage());
            return false;
        }
    }
}
