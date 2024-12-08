package com.metro_pos.View.Admin;

import javax.swing.*;
import java.awt.*;

public class AddBranchManagerDialog extends JDialog {

    public AddBranchManagerDialog(JFrame parent) {
        super(parent, "Add Branch Manager", true);

        setSize(700, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Dimension textDimension = new Dimension(200, 30);
        Font textFont = new Font("Arial", Font.PLAIN, 15);

        // Add Title Label
        JLabel titleLabel = new JLabel("ADD NEW BRANCH MANAGER");
        Font titleFont = new Font("Arial", Font.BOLD, 25);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Adding title at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 0, 20, 0); // Add some spacing around the title
        add(titleLabel, gbc);

        // Reset gridwidth and insets for other components
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Components
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(textDimension);
        nameField.setFont(textFont);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField(20);
        emailField.setPreferredSize(textDimension);
        emailField.setFont(textFont);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(textDimension);
        passwordField.setFont(textFont);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeLabel.setFont(labelFont);
        JTextField branchCodeField = new JTextField(20);
        branchCodeField.setPreferredSize(textDimension);
        branchCodeField.setFont(textFont);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(labelFont);
        JTextField salaryField = new JTextField(20);
        salaryField.setPreferredSize(textDimension);
        salaryField.setFont(textFont);

        JButton addButton = new JButton("Add Manager");
        JButton cancelButton = new JButton("Cancel");

        // Add components to layout
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(branchCodeLabel, gbc);

        gbc.gridx = 1;
        add(branchCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(salaryLabel, gbc);

        gbc.gridx = 1;
        add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Button actions
        // addButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Example: Print form data (replace with actual functionality)
        //         String name = nameField.getText();
        //         String email = emailField.getText();
        //         String password = new String(passwordField.getPassword());
        //         String branchCode = branchCodeField.getText();
        //         String salary = salaryField.getText();

        //         if (name.isEmpty() || email.isEmpty() || password.isEmpty() || branchCode.isEmpty() || salary.isEmpty()) {
        //             JOptionPane.showMessageDialog(AddBranchManagerDialog.this,
        //                     "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        //         } else {
        //             JOptionPane.showMessageDialog(AddBranchManagerDialog.this,
        //                     "Branch Manager Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        //             dispose();
        //         }
        //     }
        // });

        // cancelButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         dispose();
        //     }
        // });

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Create a parent frame
        JFrame parent = new JFrame();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setSize(800, 600);
        parent.setVisible(true);

        // Show the AddBranchManagerDialog
        new AddBranchManagerDialog(parent);
    }
}
