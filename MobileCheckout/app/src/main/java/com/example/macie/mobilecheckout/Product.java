package com.example.macie.mobilecheckout;

/**
 * Created by macie on 13-May-17.
 */

public class Product {
    private String name;
    private Long price;
    private String color;
    private String imageUrl;

    public Product(String name, Long price, String color, String imageUrl) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
