package com.franciscocasillas.cdmxgourmet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.franciscocasillas.cdmxgourmet.data.RestaurantDao;
import com.franciscocasillas.cdmxgourmet.models.Dish;

public class AddDishActivity extends AppCompatActivity {

    private EditText nameInput, priceInput, descriptionInput;
    private RadioGroup typeGroup;
    private Button saveDishButton;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Nuevo platillo");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Obtener ID del restaurante
        restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        if (restaurantId == -1) {
            Toast.makeText(this, "Error: Restaurante no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        nameInput = findViewById(R.id.editTextDishName);
        priceInput = findViewById(R.id.editTextDishPrice);
        descriptionInput = findViewById(R.id.editTextDishDescription);
        typeGroup = findViewById(R.id.radioGroupType);
        saveDishButton = findViewById(R.id.saveDishButton);

        saveDishButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String priceStr = priceInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();
            int selectedTypeId = typeGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || priceStr.isEmpty() || description.isEmpty() || selectedTypeId == -1) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double parsedPrice;
            try {
                parsedPrice = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedTypeId);
            String type = selectedRadio.getTag().toString();

            // Crear e insertar el platillo
            Dish newDish = new Dish(name, parsedPrice, description, type);
            new RestaurantDao(this).insertDishForRestaurant(restaurantId, newDish);

            Toast.makeText(this, "Platillo guardado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}