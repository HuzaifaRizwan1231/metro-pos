package com.metro_pos.Model;

public class Product {
    private int id;
    private String name;
    private String category;
    private double originalPrice;
    private double salePrice;
    private double priceByUnit;
    private double priceByCarton;
    private int quantity;
    private int vendorId;

    public Product(int id, String name, String category, double originalPrice, double salePrice, double priceByUnit,
            double priceByCarton, int quantity, int vendorId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.priceByUnit = priceByUnit;
        this.priceByCarton = priceByCarton;
        this.quantity = quantity;
        this.vendorId = vendorId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPriceByUnit() {
        return priceByUnit;
    }

    public void setPriceByUnit(double priceByUnit) {
        this.priceByUnit = priceByUnit;
    }

    public double getPriceByCarton() {
        return priceByCarton;
    }

    public void setPriceByCarton(double priceByCarton) {
        this.priceByCarton = priceByCarton;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
