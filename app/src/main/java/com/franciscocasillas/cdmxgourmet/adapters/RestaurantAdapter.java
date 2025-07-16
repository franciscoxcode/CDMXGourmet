package com.franciscocasillas.cdmxgourmet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.franciscocasillas.cdmxgourmet.R;
import com.franciscocasillas.cdmxgourmet.activities.RestaurantDetailActivity;
import com.franciscocasillas.cdmxgourmet.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurantList;
    private OnRestaurantLongClickListener longClickListener;

    public interface OnRestaurantLongClickListener {
        void onRestaurantLongClick(View view, Restaurant restaurant);
    }

    public void setOnRestaurantLongClickListener(OnRestaurantLongClickListener listener) {
        this.longClickListener = listener;
    }

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = new ArrayList<>(restaurantList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.nameTextView.setText(restaurant.name);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, RestaurantDetailActivity.class);
            intent.putExtra("restaurant_id", restaurant.id);
            intent.putExtra("restaurant_name", restaurant.name);
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onRestaurantLongClick(v, restaurant);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void updateList(List<Restaurant> newList) {
        restaurantList = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantNameTextView);
        }
    }
}