package com.metro_pos.Model;

public class SalesProducts {
    private int saleId;
    private int productId;
    private int quantitySold;
    private double priceSold;

    public SalesProducts(int saleId, int productId, int quantitySold, double priceSold) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.priceSold = priceSold;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getPriceSold() {
        return priceSold;
    }

    public void setPriceSold(double priceSold) {
        this.priceSold = priceSold;
    }
}