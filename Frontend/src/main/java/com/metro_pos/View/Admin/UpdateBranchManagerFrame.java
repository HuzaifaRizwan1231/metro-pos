package com.metro_pos.View.Admin;

import javax.swing.*;
import com.metro_pos.Database.DatabaseConnection;
import java.awt.*;

public class UpdateBranchManagerFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField salaryField;

    public UpdateBranchManagerFrame(int managerCode, String name, String email, double salary) {
        // Set up the frame
        setTitle("Update Branch Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400); 
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
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

        // Create and add the submit button with larger font
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the cancel button with larger font
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String updatedName = nameField.getText().trim();
            String updatedEmail = emailField.getText().trim();
            double updatedSalary;

            // Validate inputs
            if (updatedName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name is empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!updatedEmail.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                updatedSalary = Double.parseDouble(salaryField.getText().trim());
                if (updatedSalary <= 0) {
                    JOptionPane.showMessageDialog(this, "Salary must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call method to update
            boolean updateSuccess = updateBranchManager(managerCode, updatedName, updatedEmail, updatedSalary);

            // Provide feedback to the user
            if (updateSuccess) {
                JOptionPane.showMessageDialog(this, "Branch manager updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update branch manager. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> dispose());

        // Display the frame
        setVisible(true);
    }

    // Update branch manager method
    private boolean updateBranchManager(int managerCode, String name, String email, double salary) {
        String sql = "UPDATE user SET name = ?, email = ?, salary = ? WHERE employee_num = ?";
        try (var conn = DatabaseConnection.getConnection();
             var ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);
            ps.setInt(4, managerCode);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new UpdateBranchManagerFrame(1, "Alice Johnson", "alice@example.com", 80000);
    }
}
