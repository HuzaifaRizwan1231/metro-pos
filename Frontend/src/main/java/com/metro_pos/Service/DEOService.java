package com.metro_pos.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Database.DatabaseConnection;

public class DEOService {
    public DEOService() {
    }

    public boolean addVendor(String name, String phone, String address) {
        String sql = "INSERT INTO vendor (name, phone, address) "
                   + "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);

            // Execute the statement
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object[][] getVendors() {
        String sql = "SELECT id, name, phone, address FROM vendor";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            // Use a dynamic list to hold the data temporarily
            List<Object[]> vendorsList = new ArrayList<>();

            while (rs.next()) {
                Object[] vendor = new Object[4];
                vendor[0] = rs.getString("id");     
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
}
