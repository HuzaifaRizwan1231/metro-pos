package com.metro_pos.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;

public class DEOService {

    private Connection connection;

    public DEOService() {
        connection = DatabaseConnection.getConnection();
    }

    public int addVendor(String name, String phone, String address) {
        String sql = "INSERT INTO vendor (name, phone, address) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);

            // Execute the statement
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated key
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Object[][] getVendors() {
        String sql = "SELECT id, name, phone, address FROM vendor";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Use a dynamic list to hold the data temporarily
            List<Object[]> vendorsList = new ArrayList<>();

            while (rs.next()) {
                Object[] vendor = new Object[4];
                vendor[0] = rs.getInt("id");
                vendor[1] = rs.getString("name");
                vendor[2] = rs.getString("phone");
                vendor[3] = rs.getString("address");
                vendorsList.add(vendor);
            }

            Object[][] vendors = new Object[vendorsList.size()][4];
            for (int i = 0; i < vendorsList.size(); i++) {
                vendors[i] = vendorsList.get(i);
            }
            return vendors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int checkProductExistsInBranch(String name, int branch_id) {
        String sql = "SELECT * FROM product WHERE name = ? AND branch_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, branch_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean updateProductQuantity(int productId, int quantity, int vendor_id) {
        String sql = "UPDATE product SET quantity = quantity + ?, vendor_id = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, vendor_id);
            ps.setInt(3, productId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNewProduct(String name, String category, double originalPrice, double salePrice,
            double priceByUnit, double priceByCarton, int quantity, int vendorId, int branch_id) {
        String sql = "INSERT INTO product (name, category, original_price, sale_price, price_by_unit, price_by_carton, quantity, vendor_id, branch_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, originalPrice);
            ps.setDouble(4, salePrice);
            ps.setDouble(5, priceByUnit);
            ps.setDouble(6, priceByCarton);
            ps.setInt(7, quantity);
            ps.setInt(8, vendorId);
            ps.setInt(9, branch_id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
