package com.franciscocasillas.cdmxgourmet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        restaurantList = new ArrayList<>();

        // Tacos Don Memo
        List<Dish> memoFood = new ArrayList<>();
        memoFood.add(new Dish("Tacos al pastor", 35, "Tortillas de maíz con carne de cerdo adobada, piña y cebolla.", "placeholder.png"));
        memoFood.add(new Dish("Quesadillas de huitlacoche", 40, "Quesadillas con el hongo mexicano huitlacoche, preparadas a la plancha.", "placeholder.png"));
        List<Dish> memoDrinks = new ArrayList<>();
        memoDrinks.add(new Dish("Agua de horchata", 20, "Bebida fría de arroz con canela y azúcar.", "placeholder.png"));
        memoDrinks.add(new Dish("Refresco en lata", 18, "Lata de refresco sabor cola o limón.", "placeholder.png"));
        List<Dish> memoExtras = new ArrayList<>();
        memoExtras.add(new Dish("Guacamole", 25, "Crema de aguacate con limón, sal y cilantro.", "placeholder.png"));
        memoExtras.add(new Dish("Totopos con salsa", 15, "Trozos de tortilla frita servidos con salsa roja o verde.", "placeholder.png"));
        restaurantList.add(new Restaurant("Tacos Don Memo", memoFood, memoDrinks, memoExtras));

        // Green Vida
        List<Dish> greenFood = new ArrayList<>();
        greenFood.add(new Dish("Ensalada de quinoa", 55, "Base de quinoa con espinacas, jitomate cherry y vinagreta cítrica.", "placeholder.png"));
        greenFood.add(new Dish("Hamburguesa vegana", 65, "Pan integral con medallón de lentejas y verduras.", "placeholder.png"));
        List<Dish> greenDrinks = new ArrayList<>();
        greenDrinks.add(new Dish("Jugo verde", 30, "Mezcla de nopal, apio, manzana y limón.", "placeholder.png"));
        greenDrinks.add(new Dish("Té kombucha", 40, "Bebida fermentada de té con sabor a jengibre.", "placeholder.png"));
        List<Dish> greenExtras = new ArrayList<>();
        greenExtras.add(new Dish("Chips de kale", 25, "Hojas de kale horneadas con especias.", "placeholder.png"));
        greenExtras.add(new Dish("Hummus con zanahorias", 30, "Crema de garbanzo servida con bastones de zanahoria.", "placeholder.png"));
        restaurantList.add(new Restaurant("Green Vida", greenFood, greenDrinks, greenExtras));

        // Pizzería Napoli
        List<Dish> napoliFood = new ArrayList<>();
        napoliFood.add(new Dish("Pizza margarita", 85, "Pizza clásica con salsa de tomate, mozzarella y albahaca.", "placeholder.png"));
        napoliFood.add(new Dish("Lasaña boloñesa", 95, "Capas de pasta con carne molida, bechamel y queso.", "placeholder.png"));
        List<Dish> napoliDrinks = new ArrayList<>();
        napoliDrinks.add(new Dish("Vino tinto", 70, "Copa de vino tinto de la casa.", "placeholder.png"));
        napoliDrinks.add(new Dish("Agua mineral", 25, "Botella de agua con gas.", "placeholder.png"));
        List<Dish> napoliExtras = new ArrayList<>();
        napoliExtras.add(new Dish("Pan de ajo", 30, "Pan al horno con mantequilla, ajo y perejil.", "placeholder.png"));
        napoliExtras.add(new Dish("Ensalada caprese", 45, "Mozzarella fresca con jitomate y albahaca.", "placeholder.png"));
        restaurantList.add(new Restaurant("Pizzería Napoli", napoliFood, napoliDrinks, napoliExtras));

        // Delicias Orientales
        List<Dish> orientalFood = new ArrayList<>();
        orientalFood.add(new Dish("Ramen de cerdo", 90, "Sopa japonesa con fideos, caldo de cerdo y huevo.", "placeholder.png"));
        orientalFood.add(new Dish("Arroz frito con vegetales", 75, "Arroz salteado con verduras y salsa de soya.", "placeholder.png"));
        List<Dish> orientalDrinks = new ArrayList<>();
        orientalDrinks.add(new Dish("Té de jazmín", 25, "Té caliente con aroma floral de jazmín.", "placeholder.png"));
        orientalDrinks.add(new Dish("Cerveza asiática", 60, "Botella de cerveza importada, estilo lager.", "placeholder.png"));
        List<Dish> orientalExtras = new ArrayList<>();
        orientalExtras.add(new Dish("Gyozas", 50, "Empanadillas rellenas de carne y vegetales.", "placeholder.png"));
        orientalExtras.add(new Dish("Edamames con sal", 35, "Vainas de soya cocidas con sal de mar.", "placeholder.png"));
        restaurantList.add(new Restaurant("Delicias Orientales", orientalFood, orientalDrinks, orientalExtras));
    }
}