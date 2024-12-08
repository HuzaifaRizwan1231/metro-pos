package com.metro_pos.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Model.Product;
import com.metro_pos.Store.UserStore;

public class ProductService extends BaseService {
    public List<Product> findByKeyword(String keyword) {

        List<Product> products = new ArrayList<>();

        try {
            String query = "SELECT * FROM product WHERE (name LIKE ? OR id LIKE ?) AND branch_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setInt(3, UserStore.getCurrentUser().getBranchCode());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("original_price"),
                        rs.getDouble("sale_price"),
                        rs.getDouble("price_by_unit"),
                        rs.getDouble("price_by_carton"),
                        rs.getInt("quantity"),
                        rs.getInt("vendor_id"),
                        rs.getInt("branch_id"));
                products.add(product);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
}
