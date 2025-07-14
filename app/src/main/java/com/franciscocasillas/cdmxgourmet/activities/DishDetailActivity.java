package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.franciscocasillas.cdmxgourmet.R;

public class DishDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // 🟣 Activar layout extendido
        setContentView(R.layout.activity_dish_detail);

        // ✅ Evitar que la toolbar quede bajo el status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener datos del intent
        String name = getIntent().getStringExtra("dish_name");
        String description = getIntent().getStringExtra("dish_description");
        double price = getIntent().getDoubleExtra("dish_price", 0.0);
        String imageUrl = getIntent().getStringExtra("dish_image");

        // Configurar Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Referencias UI
        TextView nameText = findViewById(R.id.dishNameTextView);
        TextView descriptionText = findViewById(R.id.dishDescriptionTextView);
        TextView priceText = findViewById(R.id.dishPriceTextView);
        ImageView dishImageView = findViewById(R.id.dishImageView);

        // Mostrar datos
        nameText.setText(name);
        descriptionText.setText(description);
        priceText.setText("$" + price + " MXN");

        // Cargar imagen
        String imageName = imageUrl != null ? imageUrl.replace(".png", "") : "placeholder";
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        if (imageResId != 0) {
            dishImageView.setImageResource(imageResId);
        } else {
            dishImageView.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
