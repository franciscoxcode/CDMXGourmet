package com.franciscocasillas.cdmxgourmet.models;

public class Dish {
    public String name;
    public double price;
    public String description;
    public String imageUrl; // Puedes dejarlo aunque no se use aún
    public String type;     // ✅ Agregado: tipo del platillo

    public Dish(String name, double price, String description, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.imageUrl = ""; // Puedes usarlo después si agregas imágenes
    }
}