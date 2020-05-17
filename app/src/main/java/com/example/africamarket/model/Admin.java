package com.example.africamarket.model;

public class Admin {
    private String adminName;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(String username, String email, String password) {
        this.adminName = username;
        this.email = email;
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
}
