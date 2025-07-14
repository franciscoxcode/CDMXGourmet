package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
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

        // Mostrar datos en la vista
        TextView nameText = findViewById(R.id.dishNameTextView);
        TextView descriptionText = findViewById(R.id.dishDescriptionTextView);
        TextView priceText = findViewById(R.id.dishPriceTextView);

        nameText.setText(name);
        descriptionText.setText(description);
        priceText.setText("$" + price + " MXN");
    }
}
