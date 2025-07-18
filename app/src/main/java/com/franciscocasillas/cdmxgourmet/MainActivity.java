package com.franciscocasillas.cdmxgourmet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.franciscocasillas.cdmxgourmet.adapters.RestaurantAdapter;
import com.franciscocasillas.cdmxgourmet.data.RestaurantDao;
import com.franciscocasillas.cdmxgourmet.models.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public static List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 🧭 Toolbar personalizada con logo
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 🪟 Ajustes de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 📦 Cargar restaurantes desde DB
        setupRestaurants();

        // 📋 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(adapter);

        // ✏️ Long press para editar
        adapter.setOnRestaurantLongClickListener((view, restaurant) -> {
            Intent intent = new Intent(MainActivity.this, EditRestaurantActivity.class);
            intent.putExtra("restaurant_id", restaurant.id);
            intent.putExtra("restaurant_name", restaurant.name);
            startActivity(intent);
        });

        // ➕ FAB para agregar restaurante
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RestaurantDao dao = new RestaurantDao(this);
        restaurantList = dao.getAllRestaurants();
        adapter.updateList(restaurantList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterRestaurants(newText);
                    return true;
                }
            });
        }

        return true;
    }

    private void filterRestaurants(String text) {
        List<Restaurant> filteredList = restaurantList.stream()
                .filter(r -> r.name.toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filteredList);
    }

    private void setupRestaurants() {
        RestaurantDao dao = new RestaurantDao(this);
        restaurantList = dao.getAllRestaurants();
    }
}