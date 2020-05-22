package com.example.africamarket.model;

public class AdminCreation {
    private String email;
    private String code;

    public AdminCreation() {
    }

    public AdminCreation(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AdminCreation{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
