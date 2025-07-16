package com.franciscocasillas.cdmxgourmet.models;

import java.util.List;

public class Restaurant {
    public int id; // 🆔 ID necesario para editar/eliminar desde DAO
    public String name;
    public List<Dish> food;
    public List<Dish> drinks;
    public List<Dish> extras;

    // Constructor principal usado para agregar desde BD
    public Restaurant(int id, String name, List<Dish> food, List<Dish> drinks, List<Dish> extras) {
        this.id = id;
        this.name = name;
        this.food = food;
        this.drinks = drinks;
        this.extras = extras;
    }

    // Constructor auxiliar si no tienes el ID aún (ej. al insertar)
    public Restaurant(String name, List<Dish> food, List<Dish> drinks, List<Dish> extras) {
        this.name = name;
        this.food = food;
        this.drinks = drinks;
        this.extras = extras;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Dish> getFood() {
        return food;
    }

    public List<Dish> getDrinks() {
        return drinks;
    }

    public List<Dish> getExtras() {
        return extras;
    }
}