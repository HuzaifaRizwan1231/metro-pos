package com.metro_pos.View.BranchManager;

import javax.swing.*;

import com.metro_pos.Database.DatabaseConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField salaryField;
    private JComboBox<String> roleComboBox;

    public UpdateEmployeeFrame(int employeeCode, String name, String email, double salary, String role) {
        // Set up the frame
        setTitle("Update Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600); // Adjusted size
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

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(largeFont);
        salaryField = new JTextField(20);
        salaryField.setFont(largeFont);
        salaryField.setText(String.valueOf(salary));

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(largeFont);
        String[] roles = {"Cashier", "DEO"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(largeFont);
        roleComboBox.setSelectedItem(role);

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
        add(salaryLabel, gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(roleLabel, gbc);
        gbc.gridx = 1;
        add(roleComboBox, gbc);

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
    try {
        // Retrieve updated values
        String updatedName = nameField.getText();
        String updatedEmail = emailField.getText();
        String salaryText = salaryField.getText();
        String updatedRole = (String) roleComboBox.getSelectedItem();

        // Validation
        // 1. Check if name is empty
        if (updatedName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Check if email is empty or doesn't match the email pattern
        if (updatedEmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Regex pattern for email validation
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!updatedEmail.matches(emailPattern)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Check if salary is a valid positive number
        double updatedSalary;
        try {
            updatedSalary = Double.parseDouble(salaryText);
            if (updatedSalary <= 0) {
                JOptionPane.showMessageDialog(this, "Salary must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Check if role is selected
        if (updatedRole == null || updatedRole.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the employee in the database
        boolean success = updateEmployeeInDatabase(employeeCode, updatedName, updatedEmail, updatedSalary, updatedRole);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            dispose(); // Close the frame
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update the employee. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while updating the employee.", "Error", JOptionPane.ERROR_MESSAGE);
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
private boolean updateEmployeeInDatabase(int employeeCode, String name, String email, double salary, String role) {
    String sql = "UPDATE user SET name = ?, email = ?, salary = ?, role = ? WHERE employee_num = ?";
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        
        // Set parameters
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setDouble(3, salary);
        ps.setString(4, role);
        ps.setInt(5, employeeCode);

        // Execute the update query
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public static void main(String[] args) {
        new UpdateEmployeeFrame(1, "Charlie Brown", "charlie@example.com", 40000.0, "Cashier"); // Example usage
    }
}
