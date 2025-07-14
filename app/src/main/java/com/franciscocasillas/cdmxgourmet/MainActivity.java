package com.franciscocasillas.cdmxgourmet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.franciscocasillas.cdmxgourmet.models.Dish;
import com.franciscocasillas.cdmxgourmet.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.franciscocasillas.cdmxgourmet.adapters.RestaurantAdapter;

public class MainActivity extends AppCompatActivity {

    // 🔓 Hacemos restaurantList pública y estática para que pueda ser usada en otros lugares
    public static List<Restaurant> restaurantList = new ArrayList<>();

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

        // Tacos Don Memo
        List<Dish> memoFood = new ArrayList<>();
        memoFood.add(new Dish("Tacos al pastor", 35, "Tortillas de maíz con carne de cerdo adobada, piña y cebolla.", "placeholder"));
        memoFood.add(new Dish("Quesadillas de huitlacoche", 40, "Quesadillas con el hongo mexicano huitlacoche, preparadas a la plancha.", "placeholder"));
        List<Dish> memoDrinks = new ArrayList<>();
        memoDrinks.add(new Dish("Agua de horchata", 20, "Bebida fría de arroz con canela y azúcar.", "placeholder"));
        memoDrinks.add(new Dish("Refresco en lata", 18, "Lata de refresco sabor cola o limón.", "placeholder"));
        List<Dish> memoExtras = new ArrayList<>();
        memoExtras.add(new Dish("Guacamole", 25, "Crema de aguacate con limón, sal y cilantro.", "placeholder"));
        memoExtras.add(new Dish("Totopos con salsa", 15, "Trozos de tortilla frita servidos con salsa roja o verde.", "placeholder"));
        restaurantList.add(new Restaurant("Tacos Don Memo", memoFood, memoDrinks, memoExtras));

        // Green Vida
        List<Dish> greenFood = new ArrayList<>();
        greenFood.add(new Dish("Ensalada de quinoa", 55, "Base de quinoa con espinacas, jitomate cherry y vinagreta cítrica.", "placeholder"));
        greenFood.add(new Dish("Hamburguesa vegana", 65, "Pan integral con medallón de lentejas y verduras.", "placeholder"));
        List<Dish> greenDrinks = new ArrayList<>();
        greenDrinks.add(new Dish("Jugo verde", 30, "Mezcla de nopal, apio, manzana y limón.", "placeholder"));
        greenDrinks.add(new Dish("Té kombucha", 40, "Bebida fermentada de té con sabor a jengibre.", "placeholder"));
        List<Dish> greenExtras = new ArrayList<>();
        greenExtras.add(new Dish("Chips de kale", 25, "Hojas de kale horneadas con especias.", "placeholder"));
        greenExtras.add(new Dish("Hummus con zanahorias", 30, "Crema de garbanzo servida con bastones de zanahoria.", "placeholder"));
        restaurantList.add(new Restaurant("Green Vida", greenFood, greenDrinks, greenExtras));

        // Pizzería Napoli
        List<Dish> napoliFood = new ArrayList<>();
        napoliFood.add(new Dish("Pizza margarita", 85, "Pizza clásica con salsa de tomate, mozzarella y albahaca.", "placeholder"));
        napoliFood.add(new Dish("Lasaña boloñesa", 95, "Capas de pasta con carne molida, bechamel y queso.", "placeholder"));
        List<Dish> napoliDrinks = new ArrayList<>();
        napoliDrinks.add(new Dish("Vino tinto", 70, "Copa de vino tinto de la casa.", "placeholder"));
        napoliDrinks.add(new Dish("Agua mineral", 25, "Botella de agua con gas.", "placeholder"));
        List<Dish> napoliExtras = new ArrayList<>();
        napoliExtras.add(new Dish("Pan de ajo", 30, "Pan al horno con mantequilla, ajo y perejil.", "placeholder"));
        napoliExtras.add(new Dish("Ensalada caprese", 45, "Mozzarella fresca con jitomate y albahaca.", "placeholder"));
        restaurantList.add(new Restaurant("Pizzería Napoli", napoliFood, napoliDrinks, napoliExtras));

        // Delicias Orientales
        List<Dish> orientalFood = new ArrayList<>();
        orientalFood.add(new Dish("Ramen de cerdo", 90, "Sopa japonesa con fideos, caldo de cerdo y huevo.", "placeholder"));
        orientalFood.add(new Dish("Arroz frito con vegetales", 75, "Arroz salteado con verduras y salsa de soya.", "placeholder"));
        List<Dish> orientalDrinks = new ArrayList<>();
        orientalDrinks.add(new Dish("Té de jazmín", 25, "Té caliente con aroma floral de jazmín.", "placeholder"));
        orientalDrinks.add(new Dish("Cerveza asiática", 60, "Botella de cerveza importada, estilo lager.", "placeholder"));
        List<Dish> orientalExtras = new ArrayList<>();
        orientalExtras.add(new Dish("Gyozas", 50, "Empanadillas rellenas de carne y vegetales.", "placeholder"));
        orientalExtras.add(new Dish("Edamames con sal", 35, "Vainas de soya cocidas con sal de mar.", "placeholder"));
        restaurantList.add(new Restaurant("Delicias Orientales", orientalFood, orientalDrinks, orientalExtras));

        // Setup del RecyclerView
        RecyclerView recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(adapter);
    }
}
