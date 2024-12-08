package com.metro_pos.View.BranchManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.metro_pos.Database.DatabaseConnection;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageEmployeeFrame extends JFrame {

    private JButton updateEmployeeButton;
    private JButton deleteEmployeeButton;

    public ManageEmployeeFrame() {
        // Set up the frame
        setTitle("Manage Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setUndecorated(false);
        // Set layout manager
        setLayout(new BorderLayout());

        // Create the table with updated columns
        String[] columnNames = {"Employee Code", "Name", "Email", "Branch Code", "Salary", "Role"};
        Object[][] data = fetchEmployeeData(1, "Cashier", "DEO");

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Set table font size and row height
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);

        // Set column header font size
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons with larger preferred size and font
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(buttonFont);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setPreferredSize(new Dimension(200, 50));
        addEmployeeButton.setFont(buttonFont);

        updateEmployeeButton = new JButton("Update Employee");
        updateEmployeeButton.setPreferredSize(new Dimension(200, 50));
        updateEmployeeButton.setFont(buttonFont);

        deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.setPreferredSize(new Dimension(200, 50));
        deleteEmployeeButton.setFont(buttonFont);

        // Initially disable the update and delete buttons
        updateEmployeeButton.setEnabled(false);
        deleteEmployeeButton.setEnabled(false);

        buttonPanel.add(backButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(updateEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        backButton.addActionListener(e ->{
            new BranchManagerFrame();
            dispose();
        } );

        addEmployeeButton.addActionListener(e -> {
            new AddEmployeeFrame();
            // dispose();
        });

        deleteEmployeeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            int employeeCode = (int) table.getValueAt(selectedRow, 0);
            deleteEmployeeFromDatabase(employeeCode);
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "No employee selected");
            }
        });

        updateEmployeeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Retrieve selected employee details
                int employeeCode = (int) table.getValueAt(selectedRow, 0);
                String name = (String) table.getValueAt(selectedRow, 1);
                String email = (String) table.getValueAt(selectedRow, 2);
                int branchCode = (int) table.getValueAt(selectedRow, 3);
                double salary = (double) table.getValueAt(selectedRow, 4);
                String role = (String) table.getValueAt(selectedRow, 5);

                // Open the UpdateEmployeeFrame with the selected employee's details
                new UpdateEmployeeFrame(employeeCode, name, email, salary, role);
            } else {
                JOptionPane.showMessageDialog(this, "No employee selected");
            }
        });

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean isSelected = table.getSelectedRow() != -1;
                updateEmployeeButton.setEnabled(isSelected);
                deleteEmployeeButton.setEnabled(isSelected);
            }
        });

        // Display the frame
        setVisible(true);
    }

    private boolean deleteEmployeeFromDatabase(int employeeCode) {
        String sql = "DELETE FROM user WHERE employee_num = ?"; // SQL DELETE query
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
    
            // Set the employee code to the prepared statement
            ps.setInt(1, employeeCode);
    
            // Execute the DELETE statement
            int rowsAffected = ps.executeUpdate();
    
            // Return true if one or more rows were affected, meaning the employee was deleted
            return rowsAffected > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error while deleting
        }
    }
    
        private Object[][] fetchEmployeeData(int branchId, String... roles) {
        ArrayList<Object[]> employeeData = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE branch_code = ? AND role IN (?, ?)"; // Query with placeholders

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, branchId); // Set the branch ID
            ps.setString(2, roles[0]); // Set the first role
            ps.setString(3, roles[1]); // Set the second role
            
            ResultSet rs = ps.executeQuery();

            // Iterate over the result set and store the data in the ArrayList
            while (rs.next()) {
                int employeeCode = rs.getInt("employee_num");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int branchCode = rs.getInt("branch_code");
                double salary = rs.getDouble("salary");
                String role = rs.getString("role");

                employeeData.add(new Object[]{employeeCode, name, email, branchCode, salary, role});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the ArrayList to a 2D array
        return employeeData.toArray(new Object[0][0]);
    }

    public static void main(String[] args) {
        new ManageEmployeeFrame(); // Create an instance of ManageEmployeeFrame
    }
}
