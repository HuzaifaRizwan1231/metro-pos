package com.metro_pos.View.DataEntryOperator;

import javax.swing.*;

import com.metro_pos.Controller.DEOController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddNewProduct extends JDialog {

    private String vendorId;

    public AddNewProduct(Dialog parent, String vendorId) {
        super(parent, "Add New Product", true);
        this.vendorId = vendorId;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 19);

        JLabel headerLabel = new JLabel("ADD NEW PRODUCT");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        JTextField nameField = new JTextField(20);
        nameField.setFont(font);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(font);
        JTextField categoryField = new JTextField(20);
        categoryField.setFont(font);

        JLabel originalPriceLabel = new JLabel("Original Price:");
        originalPriceLabel.setFont(font);
        JTextField originalPriceField = new JTextField(20);
        originalPriceField.setFont(font);

        JLabel salePriceLabel = new JLabel("Sale Price:");
        salePriceLabel.setFont(font);
        JTextField salePriceField = new JTextField(20);
        salePriceField.setFont(font);

        JLabel priceByUnitLabel = new JLabel("Price by Unit:");
        priceByUnitLabel.setFont(font);
        JTextField priceByUnitField = new JTextField(20);
        priceByUnitField.setFont(font);

        JLabel priceByCartonLabel = new JLabel("Price by Carton:");
        priceByCartonLabel.setFont(font);
        JTextField priceByCartonField = new JTextField(20);
        priceByCartonField.setFont(font);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(font);
        JTextField quantityField = new JTextField(20);
        quantityField.setFont(font);

        JButton addButton = new JButton("Add Product");
        JButton cancelButton = new JButton("Cancel");
        addButton.setFont(font);
        cancelButton.setFont(font);

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(categoryLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(categoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(originalPriceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        add(originalPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(salePriceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        add(salePriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(priceByUnitLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        add(priceByUnitField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(priceByCartonLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        add(priceByCartonField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(quantityLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String category = categoryField.getText();
                String originalPrice = originalPriceField.getText();
                String salePrice = salePriceField.getText();
                String priceByUnit = priceByUnitField.getText();
                String priceByCarton = priceByCartonField.getText();
                String quantity = quantityField.getText();

                // Validate inputs and perform action (e.g., add to database)
                if (name.isEmpty() || category.isEmpty() || originalPrice.isEmpty() ||
                        salePrice.isEmpty() || priceByUnit.isEmpty() ||
                        priceByCarton.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(AddNewProduct.this,
                            "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                }

                Double originalPriceDouble = Double.parseDouble(originalPrice);
                Double salePriceDouble = Double.parseDouble(salePrice);
                Double priceByUnitDouble = Double.parseDouble(priceByUnit);
                Double priceByCartonDouble = Double.parseDouble(priceByCarton);
                int quantityInt = Integer.parseInt(quantity);

                if(originalPriceDouble < 0 || salePriceDouble < 0 || priceByUnitDouble < 0 || priceByCartonDouble < 0) {
                    JOptionPane.showMessageDialog(AddNewProduct.this,
                            "Prices cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(quantityInt < 0) {
                    JOptionPane.showMessageDialog(AddNewProduct.this,
                            "Quantity cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    DEOController deo = new DEOController();
                    //TODO add branch id
                    boolean result = deo.addProduct(name, category, originalPriceDouble, salePriceDouble, priceByUnitDouble, priceByCartonDouble, quantityInt, Integer.parseInt(vendorId), 1);
                    if(result) {
                        JOptionPane.showMessageDialog(AddNewProduct.this,
                                "Product added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(AddNewProduct.this,
                                "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}