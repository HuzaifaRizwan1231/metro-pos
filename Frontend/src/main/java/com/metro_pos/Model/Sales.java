package com.metro_pos.Model;

import java.sql.Timestamp;

public class Sales {
    private int id;
    private Timestamp createdAt;
    private int cashierId;
    private int branchCode;
    private int totalQuantity;
    private double totalPrice;

    public Sales(int id, Timestamp createdAt, int cashierId, int branchCode, int totalQuantity, double totalPrice) {
        this.id = id;
        this.createdAt = createdAt;
        this.cashierId = cashierId;
        this.branchCode = branchCode;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
