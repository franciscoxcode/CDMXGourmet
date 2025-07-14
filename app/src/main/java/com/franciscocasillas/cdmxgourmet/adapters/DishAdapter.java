package com.franciscocasillas.cdmxgourmet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.models.Dish;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private final List<Dish> dishList;

    public DishAdapter(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = dishList.get(position);
        holder.dishNameTextView.setText(dish.name);
        holder.dishDescriptionTextView.setText(dish.description);
        holder.dishPriceTextView.setText("$" + dish.price + " MXN");
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishNameTextView;
        TextView dishDescriptionTextView;
        TextView dishPriceTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dishNameTextView = itemView.findViewById(R.id.dishNameTextView);
            dishDescriptionTextView = itemView.findViewById(R.id.dishDescriptionTextView);
            dishPriceTextView = itemView.findViewById(R.id.dishPriceTextView);
        }
    }
}
