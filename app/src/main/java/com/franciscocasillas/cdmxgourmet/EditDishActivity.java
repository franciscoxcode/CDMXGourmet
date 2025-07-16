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

public class EditDishActivity extends AppCompatActivity {

    private EditText nameInput, priceInput, descriptionInput;
    private RadioGroup typeGroup;
    private Button updateButton, deleteButton;

    private int restaurantId;
    private int dishId;
    private String originalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Editar platillo");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Obtener datos
        restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        dishId = getIntent().getIntExtra("dish_id", -1);
        String name = getIntent().getStringExtra("dish_name");
        String description = getIntent().getStringExtra("dish_description");
        double price = getIntent().getDoubleExtra("dish_price", 0.0);
        originalType = getIntent().getStringExtra("dish_type");

        // Inicializar views
        nameInput = findViewById(R.id.editTextDishName);
        priceInput = findViewById(R.id.editTextDishPrice);
        descriptionInput = findViewById(R.id.editTextDishDescription);
        typeGroup = findViewById(R.id.radioGroupType);
        updateButton = findViewById(R.id.updateDishButton);
        deleteButton = findViewById(R.id.deleteDishButton);

        // Set datos
        nameInput.setText(name);
        priceInput.setText(String.valueOf(price));
        descriptionInput.setText(description);

        // Seleccionar tipo original
        if (originalType.equals("food")) typeGroup.check(R.id.radioFood);
        else if (originalType.equals("drink")) typeGroup.check(R.id.radioDrink);
        else if (originalType.equals("complement")) typeGroup.check(R.id.radioExtra);

        // Actualizar
        updateButton.setOnClickListener(v -> {
            String newName = nameInput.getText().toString().trim();
            String newPriceStr = priceInput.getText().toString().trim();
            String newDescription = descriptionInput.getText().toString().trim();
            int selectedTypeId = typeGroup.getCheckedRadioButtonId();

            if (newName.isEmpty() || newPriceStr.isEmpty() || selectedTypeId == -1) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double newPrice;
            try {
                newPrice = Double.parseDouble(newPriceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedTypeId);
            String newType = selectedRadio.getText().toString();
            if (newType.equals("Comida")) newType = "food";
            else if (newType.equals("Bebida")) newType = "drink";
            else if (newType.equals("Complemento")) newType = "complement";

            Dish updatedDish = new Dish(newName, newPrice, newDescription, newType);
            updatedDish.id = dishId;

            new RestaurantDao(this).updateDish(updatedDish);
            Toast.makeText(this, "Platillo actualizado", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Eliminar
        deleteButton.setOnClickListener(v -> {
            new RestaurantDao(this).deleteDish(dishId);
            Toast.makeText(this, "Platillo eliminado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}