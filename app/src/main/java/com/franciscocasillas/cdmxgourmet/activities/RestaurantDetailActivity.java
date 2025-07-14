package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.adapters.RestaurantPagerAdapter;

public class RestaurantDetailActivity extends AppCompatActivity {

    private static final String[] TAB_TITLES = new String[]{"Comida", "Bebidas", "Complementos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🧠 Obtener el nombre e índice
        String restaurantName = getIntent().getStringExtra("restaurant_name");
        int restaurantIndex = getIntent().getIntExtra("restaurant_index", 0);

        // 🏷️ Mostrar el nombre
        TextView nameTextView = findViewById(R.id.restaurantNameTextView);
        nameTextView.setText(restaurantName);

        // 📲 Configurar ViewPager y TabLayout
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new RestaurantPagerAdapter(this, restaurantIndex));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position])
        ).attach();
    }
}
