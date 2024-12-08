package com.metro_pos.View.Login;

import javax.swing.*;

import com.metro_pos.Controller.AuthController;
import com.metro_pos.Store.UserStore;
import com.metro_pos.View.BranchManager.BranchManagerFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePasswordDialog extends JDialog {

    private AuthController authController;

    public UpdatePasswordDialog(JFrame parent) {
        super(parent, "Update Password", true);

        this.authController = new AuthController();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 19);

        JLabel headerLabel = new JLabel("UPDATE PASSWORD");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(font);
        JPasswordField newPasswordField = new JPasswordField(20);
        newPasswordField.setFont(font);

        JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password:");
        confirmNewPasswordLabel.setFont(font);
        JPasswordField confirmNewPasswordField = new JPasswordField(20);
        confirmNewPasswordField.setFont(font);

        JButton updateButton = new JButton("Update Password");
        JButton cancelButton = new JButton("Cancel");
        updateButton.setFont(font);
        cancelButton.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(newPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmNewPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(confirmNewPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Add button functionality
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButton.setEnabled(false);
                String newPassword = new String(newPasswordField.getPassword());
                String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

                if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(UpdatePasswordDialog.this,
                            "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!newPassword.equals(confirmNewPassword)) {
                    JOptionPane.showMessageDialog(UpdatePasswordDialog.this,
                            "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (authController.updatePassword(newPassword)) {
                        JOptionPane.showMessageDialog(UpdatePasswordDialog.this,
                                "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        if (UserStore.getCurrentUser().getRole().equals("Manager")) {
                            new BranchManagerFrame();
                        } else if (UserStore.getCurrentUser().getRole().equals("DEO")) {
                            new com.metro_pos.View.DataEntryOperator.MainFrame();
                        } else if (UserStore.getCurrentUser().getRole().equals("Cashier")) {
                            new com.metro_pos.View.Cashier.MainFrame();
                        }
                        dispose();
                        parent.dispose();
                    } else {
                        JOptionPane.showMessageDialog(UpdatePasswordDialog.this,
                                "An Error Occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateButton.setEnabled(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
