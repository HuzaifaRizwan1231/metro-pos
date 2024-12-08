package com.metro_pos.View.BranchManager;

import javax.swing.*;

import com.metro_pos.Controller.ManagerController;
import com.metro_pos.Database.DatabaseConnection;
import com.metro_pos.Model.User;
import com.metro_pos.Store.UserStore;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField salaryField;
    private JComboBox<String> roleComboBox;

    public AddEmployeeFrame() {
        // Set up the frame
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600); // Adjusted size since we removed two fields
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

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(largeFont);
        emailField = new JTextField(20);
        emailField.setFont(largeFont);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(largeFont);
        salaryField = new JTextField(20);
        salaryField.setFont(largeFont);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(largeFont);
        String[] roles = {"Cashier", "DEO"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(largeFont);

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
            // Logic to handle adding a new employee
            
            // Retrieve input values
            String name = nameField.getText();
            String email = emailField.getText();
            String salaryText = salaryField.getText();
            String role = (String) roleComboBox.getSelectedItem();
            
            // Validation
            // 1. Check if name is empty
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // 2. Check if email is empty or doesn't match the email pattern
            if (email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Regex pattern for email validation
            String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // 3. Check if salary is a valid positive number
            double salary = 0;
            try {
                salary = Double.parseDouble(salaryText);
                if (salary <= 0) {
                    JOptionPane.showMessageDialog(this, "Salary must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // 4. Check if role is selected
            if (role == null || role.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a role.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            int branchid = 1;  // Static for now, modify as per your actual logic
            ManagerController managerController = new ManagerController();
            User u=UserStore.getCurrentUser();
            branchid=u.getBranchCode();
            managerController.addManager(name, email, branchid, salary,role);  // Corrected method call
        
            // If all validations pass, proceed with adding the employee (e.g., storing to a database or a list)
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            dispose();
        });
        
        // Add action listener for the cancel button
        cancelButton.addActionListener(e -> {
            // Logic to cancel the addition and close the frame
            dispose();
        });

        // Display the frame
        setVisible(true);


    }


    public static void main(String[] args) {
        new AddEmployeeFrame(); // Create an instance of AddEmployeeFrame
    }
}
