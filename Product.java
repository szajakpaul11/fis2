package com.company.project.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stars;
    private int quantity; // Cantitatea disponibilă în stoc
    private ProductType type; // Tipul produsului

    // Constructor
    public Product(Long id, String name, String description, double price, int stars, int quantity, ProductType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stars = stars;
        this.quantity = quantity;
        this.type = type;
    }

    // Metode getter și setter pentru id, name, description, price, stars, quantity și type

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
