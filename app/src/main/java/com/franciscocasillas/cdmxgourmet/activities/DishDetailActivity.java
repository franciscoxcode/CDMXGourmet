package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.franciscocasillas.cdmxgourmet.R;

public class DishDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);

        // Obtener datos del intent
        String name = getIntent().getStringExtra("dish_name");
        String description = getIntent().getStringExtra("dish_description");
        double price = getIntent().getDoubleExtra("dish_price", 0.0);
        String imageUrl = getIntent().getStringExtra("dish_image");

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
            dishImageView.setImageResource(R.drawable.placeholder); // Fallback si no existe
        }
    }
}
