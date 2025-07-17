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
import com.franciscocasillas.cdmxgourmet.data.RestaurantDao;
import com.franciscocasillas.cdmxgourmet.models.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DishListFragment extends Fragment {

    private static final String ARG_ID = "restaurant_id";
    private static final String ARG_CATEGORY = "category";

    private int restaurantId;
    private String category;

    private DishAdapter adapter;
    private List<Dish> originalList;

    public static DishListFragment newInstance(int id, String category) {
        DishListFragment fragment = new DishListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    public DishListFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantId = getArguments().getInt(ARG_ID);
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDishes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.dishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new DishAdapter(new ArrayList<>(), restaurantId);
        recyclerView.setAdapter(adapter);

        loadDishes();

        return view;
    }

    private void loadDishes() {
        if (getContext() == null) return;

        RestaurantDao dao = new RestaurantDao(getContext());
        originalList = dao.getDishesByType(restaurantId, category);
        adapter.updateList(originalList);
    }

    public void filter(String query) {
        if (adapter == null || originalList == null) return;

        List<Dish> filtered = originalList.stream()
                .filter(d -> d.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        adapter.updateList(filtered);
    }
}