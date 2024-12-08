package com.metro_pos.View.Login;

import javax.swing.*;

import com.metro_pos.Controller.AuthController;

import java.awt.*;

public class LoginPanel extends JPanel {

    private AuthController authController;

    public LoginPanel(String role) {
        this.authController = new AuthController();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Dimension textDimension = new Dimension(200, 30);
        Font textFont = new Font("Arial", Font.PLAIN, 15);

        JLabel roleLabel = new JLabel(role + " Login", SwingConstants.CENTER);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(roleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField();
        usernameField.setFont(textFont);
        usernameField.setPreferredSize(textDimension);
        add(usernameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(textDimension);
        add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 10, 0);
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(70, 40));
        loginButton.setFocusPainted(false);
        add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Fill All the fields");
                return;
            }

            authController.authenticate(username, password);
        });
    }
}