package com.franciscocasillas.cdmxgourmet.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.franciscocasillas.cdmxgourmet.fragments.DishListFragment;

public class RestaurantPagerAdapter extends FragmentStateAdapter {

    private final int restaurantIndex;

    public RestaurantPagerAdapter(@NonNull FragmentActivity fa, int restaurantIndex) {
        super(fa);
        this.restaurantIndex = restaurantIndex;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String type = "food";
        if (position == 1) type = "drink";
        else if (position == 2) type = "side";

        return DishListFragment.newInstance(restaurantIndex, type);
    }

    @Override
    public int getItemCount() {
        return 3; // comida, bebida, complemento
    }
}
