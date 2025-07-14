package com.franciscocasillas.cdmxgourmet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DishListFragment extends Fragment {

    private static final String ARG_RESTAURANT_INDEX = "restaurantIndex";
    private static final String ARG_DISH_TYPE = "dishType";

    private int restaurantIndex;
    private String dishType;

    public DishListFragment() {
        // Required empty public constructor
    }

    public static DishListFragment newInstance(int restaurantIndex, String dishType) {
        DishListFragment fragment = new DishListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESTAURANT_INDEX, restaurantIndex);
        args.putString(ARG_DISH_TYPE, dishType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantIndex = getArguments().getInt(ARG_RESTAURANT_INDEX);
            dishType = getArguments().getString(ARG_DISH_TYPE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Por ahora, solo mostramos texto
        TextView textView = new TextView(getContext());
        textView.setText("Restaurante #" + restaurantIndex + "\nCategoría: " + dishType);
        textView.setTextSize(20);
        return textView;
    }
}
