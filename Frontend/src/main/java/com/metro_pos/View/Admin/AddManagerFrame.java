package com.metro_pos.View.Admin;

import javax.swing.*;
import com.metro_pos.Controller.ManagerController;
import com.metro_pos.Controller.BranchController;
import java.awt.*;
import java.util.List;

public class AddManagerFrame extends JFrame {

    public AddManagerFrame() {
        // Set up the frame
        setTitle("Add Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600); // Adjusted size due to phone and address field removal
        setLocationRelativeTo(null); // Center the frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Define larger font
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        // Create and add form labels and fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(largeFont);
        JTextField nameField = new JTextField(20);
        nameField.setFont(largeFont);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(largeFont);
        JTextField emailField = new JTextField(20);
        emailField.setFont(largeFont);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(largeFont);

        // Create a dropdown for branch codes
        JComboBox<Integer> branchCodeComboBox = new JComboBox<>();
        branchCodeComboBox.setFont(largeFont);
        populateBranchCodes(branchCodeComboBox); // Populate the dropdown with branch codes

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(largeFont);
        JTextField salaryField = new JTextField(20);
        salaryField.setFont(largeFont);

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

        // Create and add the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Create and add the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Retrieve input from fields
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            Integer branchCode = (Integer) branchCodeComboBox.getSelectedItem(); // Get branch code from the combo box
            String salary = salaryField.getText().trim();

            // Input validation
            if (name.isEmpty() || email.isEmpty() || branchCode == null || salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate salary is numeric
            try {
                double salaryDouble = Double.parseDouble(salary);

                // Call the controller to handle adding the manager
                ManagerController managerController = new ManagerController();
                boolean isAdded = managerController.addManager(name, email, branchCode, salaryDouble,"Manager");  // Corrected method call

                // Provide feedback based on the operation result
                if (isAdded) {
                    JOptionPane.showMessageDialog(this, "Manager added successfully!");
                    dispose(); // Close the frame
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add manager. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        
        
        });

        // Add action listener for the back button
        backButton.addActionListener(e -> {
            dispose(); // Close the frame
        });

        // Display the frame
        setVisible(true);
    }

    private void populateBranchCodes(JComboBox<Integer> branchCodeComboBox) {
        BranchController branchController = new BranchController();
        List<Integer> branchCodes = branchController.getUnassignedBranchCodes();

        if (branchCodes != null) {
            for (Integer code : branchCodes) {
                branchCodeComboBox.addItem(code);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to retrieve branch codes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AddManagerFrame(); // Create an instance of AddManagerFrame
    }
}
