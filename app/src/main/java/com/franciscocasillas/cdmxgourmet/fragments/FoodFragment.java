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

import com.franciscocasillas.cdmxgourmet.MainActivity;
import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.adapters.DishAdapter;
import com.franciscocasillas.cdmxgourmet.models.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodFragment extends Fragment {

    private List<Dish> allDishes;
    private DishAdapter adapter;

    public static FoodFragment newInstance(int index) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.dishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int restaurantIndex = getArguments().getInt("index");
        allDishes = MainActivity.restaurantList.get(restaurantIndex).getFood();

        adapter = new DishAdapter(new ArrayList<>(allDishes));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void filter(String query) {
        List<Dish> filtered = allDishes.stream()
                .filter(d -> d.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filtered);
    }
}
