package com.metro_pos.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metro_pos.Model.Product;
import com.metro_pos.Service.ProductService;

public class ProductController {
    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public List<Product> fetchSuggestions(String keyword) {
        return productService.findByKeyword(keyword);
    }
}
