package com.franciscocasillas.cdmxgourmet.models;

import java.util.List;

public class Restaurant {
    public String name;
    public List<Dish> food;
    public List<Dish> drinks;
    public List<Dish> extras;

    public Restaurant(String name, List<Dish> food, List<Dish> drinks, List<Dish> extras) {
        this.name = name;
        this.food = food;
        this.drinks = drinks;
        this.extras = extras;
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
