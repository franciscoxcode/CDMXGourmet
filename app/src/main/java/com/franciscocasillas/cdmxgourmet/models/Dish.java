package com.franciscocasillas.cdmxgourmet.models;

public class Dish {
    public int id;
    public String name;
    public double price;
    public String description;
    public String imageUrl;
    public String type;

    public Dish(String name, double price, String description, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.imageUrl = "";
    }
}