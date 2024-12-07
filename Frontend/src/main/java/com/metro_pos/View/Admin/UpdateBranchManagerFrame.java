package com.metro_pos.View.Admin;

import javax.swing.*;
import com.metro_pos.Database.DatabaseConnection;
import java.awt.*;
import java.sql.*;

public class UpdateBranchManagerFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JComboBox<Integer> branchCodeComboBox; // Changed to JComboBox
    private JTextField salaryField;

    public UpdateBranchManagerFrame(int managerCode, String name, String email, int branchCode, double salary) {
        // Set up the frame
        setTitle("Update Branch Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500); // Adjusted size after removing address field
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Define larger font
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        // Create and add form labels and fields with larger font
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(largeFont);
        nameField = new JTextField(20);
        nameField.setFont(largeFont);
        nameField.setText(name);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(largeFont);
        emailField = new JTextField(20);
        emailField.setFont(largeFont);
        emailField.setText(email);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(largeFont);

        // Create a JComboBox for the branch codes (Initially empty)
        branchCodeComboBox = new JComboBox<>();
        branchCodeComboBox.setFont(largeFont);

        // Load branch codes where managers are not assigned
        loadBranchCodes();

        // Set the initial selection of the branch code (set the current branch code of the manager)
        branchCodeComboBox.setSelectedItem(branchCode);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(largeFont);
        salaryField = new JTextField(20);
        salaryField.setFont(largeFont);
        salaryField.setText(String.valueOf(salary));

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(branchCodeLabel, gbc);
        gbc.gridx = 1;
        add(branchCodeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(salaryLabel, gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        // Create and add the submit button with larger font
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the cancel button with larger font
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Logic to handle updating the branch manager
            String updatedName = nameField.getText();
            String updatedEmail = emailField.getText();
            int updatedBranchCode = (int) branchCodeComboBox.getSelectedItem(); // Get selected branch code
            double updatedSalary = 0;

            // Validate salary input
            try {
                updatedSalary = Double.parseDouble(salaryField.getText());
                if (updatedSalary <= 0) {
                    JOptionPane.showMessageDialog(this, "Salary must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call a method to update the branch manager (e.g., database update)
            boolean updateSuccess = updateBranchManager(managerCode, updatedName, updatedEmail, updatedBranchCode, updatedSalary);

            // Provide feedback to the user
            if (updateSuccess) {
                JOptionPane.showMessageDialog(this, "Branch manager updated successfully!");
                dispose(); // Close the frame
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update branch manager. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> {
            // Logic to cancel the update and close the frame
            dispose();
        });

        // Display the frame
        setVisible(true);
    }

    // Method to update the branch manager in the database or system
    private boolean updateBranchManager(int managerCode, String name, String email, int branchCode, double salary) {
        // Database connection and update logic goes here
        String sql = "UPDATE user SET name = ?, email = ?, branch_code = ?, salary = ? WHERE employee_num = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, branchCode);
            ps.setDouble(4, salary);
            ps.setInt(5, managerCode);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to load branch codes where managers are not assigned
    private void loadBranchCodes() {
        String sql = "SELECT branch_code FROM branch WHERE manager_assigned = false";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Add branch codes to the combo box
            while (rs.next()) {
                int branchCode = rs.getInt("branch_code");
                branchCodeComboBox.addItem(branchCode);
            }

            // If no branch codes are available, show a message to the user
            if (branchCodeComboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "No branches available for assignment.", "No Branches", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading branch codes.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new UpdateBranchManagerFrame(1, "Alice Johnson", "alice@example.com", 1, 80000); // Example usage
    }
}
