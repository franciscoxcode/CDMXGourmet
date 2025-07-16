package com.franciscocasillas.cdmxgourmet.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.franciscocasillas.cdmxgourmet.R;

public class AddDishActivity extends AppCompatActivity {

    private EditText nameInput, priceInput, descriptionInput;
    private RadioGroup typeGroup;
    private Button saveDishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        nameInput = findViewById(R.id.editTextDishName);
        priceInput = findViewById(R.id.editTextDishPrice);
        descriptionInput = findViewById(R.id.editTextDishDescription);
        typeGroup = findViewById(R.id.radioGroupType);
        saveDishButton = findViewById(R.id.saveDishButton);

        saveDishButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String price = priceInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();
            int selectedTypeId = typeGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || price.isEmpty() || description.isEmpty() || selectedTypeId == -1) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedTypeId);
            String type = selectedRadio.getText().toString();

            // Aquí se guarda el platillo. Puedes integrar tu lógica DAO o pasar los datos de regreso.
            Toast.makeText(this, "Platillo agregado: " + name + " (" + type + ")", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}