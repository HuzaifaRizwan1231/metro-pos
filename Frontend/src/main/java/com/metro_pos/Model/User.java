package com.metro_pos.Model;

public class User {
    private int employeeNum;
    private String name;
    private String email;
    private String password;
    private int branchCode;
    private long salary;
    private String role;
    private boolean isFirstLogin;

    public User(int employeeNum, String name, String email, String password, int branchCode, long salary, String role,
            boolean isFirstLogin) {
        this.employeeNum = employeeNum;
        this.name = name;
        this.email = email;
        this.password = password;
        this.branchCode = branchCode;
        this.salary = salary;
        this.role = role;
        this.isFirstLogin = isFirstLogin;
    }

    // Getters and Setters
    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}
