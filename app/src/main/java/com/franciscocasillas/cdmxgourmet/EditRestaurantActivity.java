package com.franciscocasillas.cdmxgourmet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.franciscocasillas.cdmxgourmet.data.RestaurantDao;

public class EditRestaurantActivity extends AppCompatActivity {

    private EditText editTextRestaurantName;
    private Button updateButton, deleteButton;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_restaurant);

        // Toolbar con botón de back
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Editar restaurante");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editRestaurantLayout), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        editTextRestaurantName = findViewById(R.id.editTextRestaurantName);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        String restaurantName = getIntent().getStringExtra("restaurant_name");

        if (restaurantId == -1 || restaurantName == null) {
            Toast.makeText(this, "Error al cargar restaurante", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        editTextRestaurantName.setText(restaurantName);

        updateButton.setOnClickListener(v -> {
            String newName = editTextRestaurantName.getText().toString().trim();
            if (newName.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            new RestaurantDao(this).updateRestaurant(restaurantId, newName);
            Toast.makeText(this, "Restaurante actualizado", Toast.LENGTH_SHORT).show();
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            new RestaurantDao(this).deleteRestaurant(restaurantId);
            Toast.makeText(this, "Restaurante eliminado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    // Manejar clic en flecha hacia atrás
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}