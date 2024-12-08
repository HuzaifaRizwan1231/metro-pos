package com.metro_pos.View.DataEntryOperator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.metro_pos.Controller.DEOController;

class ChooseVendorDialogue extends JDialog {
    public ChooseVendorDialogue(JDialog parent) {
        super(parent, "Choose Vendor", true);
        DEOController deoController = new DEOController();
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Add search bar at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JTextField searchBar = new JTextField();
        add(new JLabel("Search: "), gbc);

        gbc.gridx = 1;
        add(searchBar, gbc);

        // Add table in the middle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;

        String[] columnNames = {"ID", "Name", "Phone", "Address"};
        Object[][] data = deoController.getVendors();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable vendorTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vendorTable);
        add(tableScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        JButton chooseButton = new JButton("Choose");
        chooseButton.setEnabled(false); // Initially disabled
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewProduct(parent);
                dispose();
            }
        });
        add(chooseButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton, gbc);

        // Enable "Choose" button only when a row is selected
        vendorTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                chooseButton.setEnabled(vendorTable.getSelectedRow() != -1);
            }
        });

        // Implement search functionality
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        vendorTable.setRowSorter(rowSorter);

        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String searchText = searchBar.getText();
                if (searchText.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        setVisible(true);
    }
}
