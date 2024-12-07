package com.metro_pos.View.BranchManager;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField salaryField;
    private JComboBox<String> roleComboBox;

    public UpdateEmployeeFrame(int employeeCode, String name, String email, String phone, String address, double salary, String role) {
        // Set up the frame
        setTitle("Update Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 700); // Increased size
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

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(largeFont);
        phoneField = new JTextField(20);
        phoneField.setFont(largeFont);
        phoneField.setText(phone);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(largeFont);
        addressField = new JTextField(20);
        addressField.setFont(largeFont);
        addressField.setText(address);

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
        add(phoneLabel, gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(addressLabel, gbc);
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(salaryLabel, gbc);
        gbc.gridx = 1;
        add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(roleLabel, gbc);
        gbc.gridx = 1;
        add(roleComboBox, gbc);

        // Create and add the submit button with larger font
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the cancel button with larger font
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Logic to handle updating the employee
            String updatedName = nameField.getText();
            String updatedEmail = emailField.getText();
            String updatedPhone = phoneField.getText();
            String updatedAddress = addressField.getText();
            double updatedSalary = Double.parseDouble(salaryField.getText());
            String updatedRole = (String) roleComboBox.getSelectedItem();

            // Validate and process the input as necessary
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");

            // Optionally, close the frame after submission
            dispose();
        });

        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> {
            // Logic to cancel the update and close the frame
            dispose();
        });

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateEmployeeFrame(1, "Charlie Brown", "charlie@example.com", "123-456-7890", "123 Main St", 40000.0, "Cashier"); // Example usage
    }
}
