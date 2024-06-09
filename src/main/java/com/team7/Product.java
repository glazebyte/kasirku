package com.team7;

import java.awt.image.BufferedImage;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private BufferedImage image;
    private int product_type;

    // Constructor
    public Product(int productId, String name, double price, int stock, int product_type) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.product_type = product_type;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductType(){
        return product_type;
    }

    public void setProductType(int product_type){
        this.product_type = product_type;
    }
}

