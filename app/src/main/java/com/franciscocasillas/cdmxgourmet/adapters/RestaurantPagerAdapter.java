package com.franciscocasillas.cdmxgourmet.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.franciscocasillas.cdmxgourmet.fragments.DishListFragment;

import java.util.HashMap;

public class RestaurantPagerAdapter extends FragmentStateAdapter {

    private final int restaurantId;
    private final HashMap<Integer, DishListFragment> fragmentMap = new HashMap<>();

    public RestaurantPagerAdapter(@NonNull FragmentActivity fa, int restaurantId) {
        super(fa);
        this.restaurantId = restaurantId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String type;
        switch (position) {
            case 1:
                type = "drink";
                break;
            case 2:
                type = "complement";
                break;
            default:
                type = "food";
                break;
        }

        DishListFragment fragment = DishListFragment.newInstance(restaurantId, type);
        fragmentMap.put(position, fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public DishListFragment getFragment(int position) {
        return fragmentMap.get(position);
    }
}