package com.metro_pos.View.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseRolePanelView extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ChooseRolePanelView(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Panel settings
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();

        String[] roles = { "Admin Login", "Manager Login", "Cashier Login", "Data Operator Login" };
        for (int i = 0; i < roles.length; i++) {
            JButton button = new JButton(roles[i]);
            button.setPreferredSize(new Dimension(150, 100));
            button.addActionListener(new RoleButtonListener(roles[i]));
            button.setFocusPainted(false);

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(1, 10, 1, 10);

            add(button, gbc);
        }

        JButton button = new JButton("Exit");
        button.setPreferredSize(new Dimension(150, 100));
        button.addActionListener(e -> {
            System.exit(0);
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(1, 10, 1, 10);
        button.setFocusPainted(false);
        add(button, gbc);
    }

    private class RoleButtonListener implements ActionListener {
        private String role;

        public RoleButtonListener(String role) {
            this.role = role;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch to the corresponding login panel based on the button clicked
            cardLayout.show(mainPanel, role);
        }
    }
}