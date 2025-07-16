package com.franciscocasillas.cdmxgourmet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.franciscocasillas.cdmxgourmet.AddDishActivity;
import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.adapters.RestaurantPagerAdapter;
import com.franciscocasillas.cdmxgourmet.fragments.DishListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RestaurantDetailActivity extends AppCompatActivity {

    private static final String[] TAB_TITLES = new String[]{"Comida", "Bebidas", "Complementos"};
    private int restaurantId;

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

        // Obtener datos del intent
        String restaurantName = getIntent().getStringExtra("restaurant_name");
        restaurantId = getIntent().getIntExtra("restaurant_id", -1);

        // Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(restaurantName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // ViewPager y tabs
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new RestaurantPagerAdapter(this, restaurantId));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position])
        ).attach();

        // FAB ➕ para añadir platillo
        FloatingActionButton fab = findViewById(R.id.fabAddDish);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddDishActivity.class);
            intent.putExtra("restaurant_id", restaurantId);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    applyFilter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    applyFilter(newText);
                    return true;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void applyFilter(String query) {
        int currentTab = ((ViewPager2) findViewById(R.id.viewPager)).getCurrentItem();
        RestaurantPagerAdapter adapter = (RestaurantPagerAdapter) ((ViewPager2) findViewById(R.id.viewPager)).getAdapter();

        if (adapter != null) {
            DishListFragment fragment = adapter.getFragment(currentTab);
            if (fragment != null) {
                fragment.filter(query);
            }
        }
    }
}