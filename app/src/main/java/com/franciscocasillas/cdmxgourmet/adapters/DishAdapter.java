package com.franciscocasillas.cdmxgourmet.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.activities.DishDetailActivity;
import com.franciscocasillas.cdmxgourmet.EditDishActivity;
import com.franciscocasillas.cdmxgourmet.models.Dish;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private List<Dish> dishList;
    private int restaurantId;

    public DishAdapter(List<Dish> dishList, int restaurantId) {
        this.dishList = dishList;
        this.restaurantId = restaurantId;
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
        holder.dishPriceTextView.setText("$" + dish.price + " MXN");
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public void updateList(List<Dish> newList) {
        dishList.clear();
        dishList.addAll(newList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishNameTextView;
        TextView dishPriceTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dishNameTextView = itemView.findViewById(R.id.dishNameTextView);
            dishPriceTextView = itemView.findViewById(R.id.dishPriceTextView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Dish clickedDish = dishList.get(position);

                    Intent intent = new Intent(v.getContext(), DishDetailActivity.class);
                    intent.putExtra("dish_name", clickedDish.name);
                    intent.putExtra("dish_description", clickedDish.description);
                    intent.putExtra("dish_price", clickedDish.price);
                    intent.putExtra("dish_image", clickedDish.imageUrl);
                    v.getContext().startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Dish dish = dishList.get(position);

                    Intent intent = new Intent(v.getContext(), EditDishActivity.class);
                    intent.putExtra("restaurant_id", restaurantId);
                    intent.putExtra("dish_id", dish.id); // ✅ necesario para editar/eliminar
                    intent.putExtra("dish_name", dish.name);
                    intent.putExtra("dish_price", dish.price);
                    intent.putExtra("dish_description", dish.description);
                    intent.putExtra("dish_type", dish.type);
                    v.getContext().startActivity(intent);
                }
                return true;
            });
        }
    }
}