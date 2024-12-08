package com.metro_pos.View.Login;

import javax.swing.*;

import com.metro_pos.Controller.AuthController;
import com.metro_pos.Model.User;
import com.metro_pos.Store.UserStore;
import com.metro_pos.View.Admin.AdminFrame;
import com.metro_pos.View.BranchManager.BranchManagerFrame;
import com.metro_pos.View.DataEntryOperator.MainFrame;

import java.awt.*;

public class LoginPanel extends JPanel {

    private AuthController authController;

    public LoginPanel(String role, JFrame mainFrame) {
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
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        add(emailLabel, gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField();
        emailField.setFont(textFont);
        emailField.setPreferredSize(textDimension);
        add(emailField, gbc);

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
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Fill All the fields");
                return;
            }

            if (email.equals("admin@example.com") && password.equals("admin")) {
                new AdminFrame();
                mainFrame.dispose();
                return;
            }

            Boolean response = authController.authenticate(email, password, role);

            if (response) {
                User currentUser = UserStore.getCurrentUser();

                if (currentUser.getIsFirstLogin()) {
                    // redirect to change password
                    new UpdatePasswordDialog(mainFrame);

                } else {
                    if (role.equals("Manager")) {
                        new BranchManagerFrame();
                    } else if (role.equals("DEO")) {
                        new MainFrame();
                    } else if (role.equals("Cashier")) {
                        new com.metro_pos.View.Cashier.MainFrame();
                    }
                    mainFrame.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Credentials");
            }
        });
    }
}