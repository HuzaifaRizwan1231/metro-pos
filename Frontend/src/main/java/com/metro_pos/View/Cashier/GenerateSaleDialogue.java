package com.metro_pos.View.Cashier;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.metro_pos.Controller.ProductController;
import com.metro_pos.Controller.SalesController;
import com.metro_pos.Model.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateSaleDialogue extends JDialog {

    private JTextField productField;
    private JPopupMenu suggestionPopup;
    private JTable suggestionTable;
    private JTable productTable;
    private DefaultTableModel suggestionTableModel;
    private DefaultTableModel saleTableModel;
    private ProductController productController;
    private SalesController salesController;
    private List<Product> productInSale;
    private List<Product> suggestions;
    private JButton generateSaleButton;

    private JLabel totalLabel; // JLabel to display the total amount

    public GenerateSaleDialogue(JFrame parent) {
        super(parent, "Generate Sale", true);
        setLayout(new BorderLayout());

        this.productController = new ProductController();
        this.salesController = new SalesController();
        this.productInSale = new ArrayList<>();

        String[] columnNames = { "ID", "Name", "Quantity", "Unit Price", "Total Price" };
        saleTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Only allow editing the "Quantity" column
            }
        };

        productTable = new JTable();
        productTable.setModel(saleTableModel);
        productTable.setRowHeight(25);
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.putClientProperty("terminateEditOnFocusLost", true);

        centerTableText(productTable);

        JScrollPane scrollPane = new JScrollPane(productTable);

        JPanel addItemPanel = createAddItemPanel();
        JPanel buttonPanel = createButtonPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(addItemPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        totalLabel = new JLabel("Total: Rs. 0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(totalLabel);

        setSize(800, 600);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        saleTableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 2) {
                try {
                    int newQuantity = Integer.parseInt(saleTableModel.getValueAt(row, column).toString());
                    if (newQuantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        updateSaleTable();
                        return;
                    }
                    int productId = (int) saleTableModel.getValueAt(row, 0);
                    productInSale.stream()
                            .filter(p -> p.getId() == productId)
                            .findFirst()
                            .ifPresent(p -> p.setQuantity(newQuantity));
                    updateSaleTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    updateSaleTable();
                }
            }
        });
    }

    private JPanel createAddItemPanel() {
        JPanel addItemPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel productLabel = new JLabel("Product:");
        productField = new JTextField(15);

        suggestionPopup = new JPopupMenu();
        suggestionTable = new JTable();
        suggestionTableModel = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
        suggestionTable.setModel(suggestionTableModel);
        suggestionTable.setRowHeight(25);
        suggestionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane suggestionScrollPane = new JScrollPane(suggestionTable);
        suggestionScrollPane.setPreferredSize(new Dimension(300, 150));
        suggestionPopup.add(suggestionScrollPane);

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

        suggestionTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && suggestionTable.getSelectedRow() != -1) {
                int selectedRow = suggestionTable.getSelectedRow();
                int id = Integer.parseInt(suggestionTableModel.getValueAt(selectedRow, 0).toString());
                addItem(id);
                suggestionPopup.setVisible(false);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        addItemPanel.add(productLabel, gbc);
        gbc.gridx = 1;
        addItemPanel.add(productField, gbc);

        return addItemPanel;
    }

    private JPanel createButtonPanel() {
        generateSaleButton = new JButton("Generate Sale");
        generateSaleButton.addActionListener(e -> {
            generateSaleButton.setEnabled(false);
            if (salesController.generateSale(productInSale)) {
                JOptionPane.showMessageDialog(this, "Sale generated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "An Error Occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            generateSaleButton.setEnabled(true);
        });

        JButton deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(e -> deleteSelectedItem());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(generateSaleButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private void updateSuggestions() {
        String input = productField.getText().trim();
        suggestionTableModel.setRowCount(0);

        if (input.isEmpty()) {
            suggestionPopup.setVisible(false);
            return;
        }

        suggestions = productController.fetchSuggestions(input);

        if (!suggestions.isEmpty()) {
            for (Product suggestion : suggestions) {
                suggestionTableModel.addRow(new Object[] { suggestion.getId(), suggestion.getName() });
            }
        } else {
            suggestionTableModel.addRow(new Object[] { "-", "-" });
        }

        SwingUtilities.invokeLater(() -> {
            suggestionPopup.setFocusable(false);
            suggestionPopup.show(productField, 0, productField.getHeight());
            productField.requestFocusInWindow();
        });
    }

    private void addItem(int id) {
        Product selectedProduct = suggestions.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "Selected product not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean present = false;
        for (Product p : productInSale) {
            if (p.getId() == selectedProduct.getId()) {

                if (selectedProduct.getQuantity() < p.getQuantity() + 1) {
                    JOptionPane.showMessageDialog(this, "Out of stock");
                    return;
                }
                p.setQuantity(p.getQuantity() + 1);
                present = true;
                break;
            }
        }

        if (!present) {
            if (selectedProduct.getQuantity() < 1) {
                JOptionPane.showMessageDialog(this, "Out of stock");
                return;
            }
            selectedProduct.setQuantity(1);
            productInSale.add(selectedProduct);
        }

        updateSaleTable();
        productField.setText("");
    }

    private void deleteSelectedItem() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int productId = (int) saleTableModel.getValueAt(selectedRow, 0);
        productInSale.removeIf(p -> p.getId() == productId);
        updateSaleTable();
    }

    private void updateSaleTable() {
        saleTableModel.setRowCount(0);

        double totalAmount = 0;
        for (Product p : productInSale) {
            double totalPrice = p.getPriceByUnit() * p.getQuantity();
            saleTableModel.addRow(new Object[] {
                    p.getId(),
                    p.getName(),
                    p.getQuantity(),
                    p.getPriceByUnit(),
                    totalPrice
            });
            totalAmount += totalPrice;
        }

        totalLabel.setText(String.format("Total: Rs. %.2f", totalAmount));
    }

    private void centerTableText(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

}
