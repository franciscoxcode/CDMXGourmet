package com.franciscocasillas.cdmxgourmet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.franciscocasillas.cdmxgourmet.data.RestaurantDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText editTextRestaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // ✅ Asegura que el ID exista antes de aplicar insets
        View root = findViewById(R.id.main);
        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 🔠 Referencia al campo de texto
        editTextRestaurantName = findViewById(R.id.editTextRestaurantName);

        // ➕ Botón para guardar restaurante
        Button saveButton = findViewById(R.id.saveRestaurantButton);        saveButton.setOnClickListener(v -> {
            String name = editTextRestaurantName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show();
                return;
            }

            // 💾 Guardar en base de datos
            RestaurantDao dao = new RestaurantDao(this);
            dao.insertRestaurant(name);

            Toast.makeText(this, "Restaurante guardado", Toast.LENGTH_SHORT).show();

            // ⬅️ Regresar a la pantalla anterior
            finish();
        });
    }
}