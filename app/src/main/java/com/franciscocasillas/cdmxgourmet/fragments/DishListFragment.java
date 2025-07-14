package com.franciscocasillas.cdmxgourmet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.adapters.DishAdapter;
import com.franciscocasillas.cdmxgourmet.models.Dish;
import com.franciscocasillas.cdmxgourmet.models.Restaurant;
import com.franciscocasillas.cdmxgourmet.MainActivity;

import java.util.List;

public class DishListFragment extends Fragment {

    private static final String ARG_INDEX = "index";
    private static final String ARG_CATEGORY = "category";

    private int restaurantIndex;
    private String category;

    public static DishListFragment newInstance(int index, String category) {
        DishListFragment fragment = new DishListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    public DishListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantIndex = getArguments().getInt(ARG_INDEX);
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.dishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Restaurant restaurant = MainActivity.restaurantList.get(restaurantIndex);
        List<Dish> data;

        switch (category) {
            case "food":
                data = restaurant.getFood();
                break;
            case "drink":
                data = restaurant.getDrinks();
                break;
            case "side":
                data = restaurant.getExtras();
                break;
            default:
                data = null;
                break;
        }

        DishAdapter adapter = new DishAdapter(data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
