package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.franciscocasillas.cdmxgourmet.R;
import com.squareup.picasso.Picasso;

public class DishDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dish_detail);

        // 🛠️ Ajustar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🧠 Obtener datos del intent
        String name = getIntent().getStringExtra("dish_name");
        String description = getIntent().getStringExtra("dish_description");
        double price = getIntent().getDoubleExtra("dish_price", 0.0);
        String imageUrl = getIntent().getStringExtra("dish_image");

        // 🧭 Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 🎯 Referencias de vista
        TextView nameTextView = findViewById(R.id.dishNameTextView);
        TextView priceTextView = findViewById(R.id.dishPriceTextView);
        TextView descriptionTextView = findViewById(R.id.dishDescriptionTextView);
        ImageView imageView = findViewById(R.id.dishImageView);

        // 🖼️ Cargar información
        nameTextView.setText(name);
        priceTextView.setText(String.format("$%.2f MXN", price));
        descriptionTextView.setText(description);

        // 🖼️ Cargar imagen (con fallback a placeholder)
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.placeholder);
        }
    }

    // ⬅️ Botón de regreso
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
