package com.metro_pos.View.Cashier;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddProductToSale extends JDialog {

    private JTextField productField;
    private JTextField quantityField;
    private JPopupMenu suggestionPopup;
    private DefaultListModel<String> suggestionModel;

    public AddProductToSale(Dialog parent) {
        super(parent, "Add Product to Sale", true);
        setLayout(new BorderLayout());

        Font font = new Font(null, Font.PLAIN, 16);

        // Create input fields
        JLabel productLabel = new JLabel("Product:");
        productField = new JTextField(20);
        productField.setFont(font);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        quantityField.setFont(font);

        // Create suggestion popup
        suggestionPopup = new JPopupMenu();
        JList<String> suggestionList = new JList<>();
        suggestionModel = new DefaultListModel<>();
        suggestionList.setModel(suggestionModel);
        suggestionPopup.add(new JScrollPane(suggestionList));
        suggestionList.setVisibleRowCount(5);

        // Add DocumentListener for dynamic suggestions
        productField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        // Add action to select a suggestion
        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && suggestionList.getSelectedValue() != null) {
                productField.setText(suggestionList.getSelectedValue());
                suggestionPopup.setVisible(false);
            }
        });

        // Buttons
        JButton addButton = new JButton("Add Item");
        JButton cancelButton = new JButton("Cancel");

        // Add button functionality
        addButton.addActionListener(e -> addItem());
        cancelButton.addActionListener(e -> dispose());

        // Layout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(productLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        inputPanel.add(productField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(quantityLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(quantityField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);   
    }

    private void updateSuggestions() {
        String input = productField.getText().trim();
        if (input.isEmpty()) {
            suggestionPopup.setVisible(false);
            return;
        }

        // Query database or filter list to get suggestions
        List<String> suggestions = fetchSuggestions(input);

        // Update suggestion list
        suggestionModel.clear();
        if (!suggestions.isEmpty()) {
            for (String suggestion : suggestions) {
                suggestionModel.addElement(suggestion);
            }
            suggestionPopup.show(productField, 0, productField.getHeight());
        } else {
            suggestionPopup.setVisible(false);
        }
    }

    private List<String> fetchSuggestions(String input) {
        List<String> suggestions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")) {
            String query = "SELECT name FROM product WHERE name LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, input + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        suggestions.add(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return suggestions;
    }

    private void addItem() {
        String productName = productField.getText();
        String quantityText = quantityField.getText();

        if (productName.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be positive.");
            }

            // Here, add logic to add the product and quantity to the sale
            JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

