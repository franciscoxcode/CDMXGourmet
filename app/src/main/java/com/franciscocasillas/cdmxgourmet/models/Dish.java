package com.franciscocasillas.cdmxgourmet.models;

public class Dish {
    public String name;
    public double price;
    public String description;
    public String imageUrl;

    public Dish(String name, double price, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
