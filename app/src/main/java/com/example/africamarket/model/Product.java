package com.example.africamarket.model;

public class Product {
    private String type;
    private String name;
    private String price;
    private String description;
    private String imageUrl;

    public Product() {
    }

    public Product(String type, String name, String description, String price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String type, String name, String description, String price, String imageUrl) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
