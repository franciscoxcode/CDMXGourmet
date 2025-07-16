package com.franciscocasillas.cdmxgourmet.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.franciscocasillas.cdmxgourmet.models.Dish;
import com.franciscocasillas.cdmxgourmet.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {
    private final DatabaseHelper dbHelper;

    public RestaurantDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // 🔁 Obtener todos los restaurantes (sin platillos asociados directamente)
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_RESTAURANTS,
                new String[]{"_id", "name"},
                null, null, null, null, null
        );

        while (cursor.moveToNext()) {
            Restaurant restaurant = new Restaurant(
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    new ArrayList<>(), // food
                    new ArrayList<>(), // drinks
                    new ArrayList<>()  // extras
            );
            restaurant.id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            restaurantList.add(restaurant);
        }

        cursor.close();
        db.close();
        return restaurantList;
    }

    // ➕ Insertar restaurante
    public long insertRestaurant(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        long newId = db.insert(DatabaseHelper.TABLE_RESTAURANTS, null, values);
        db.close();
        return newId;
    }

    // ✏️ Actualizar restaurante
    public int updateRestaurant(int id, String newName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        int rows = db.update(DatabaseHelper.TABLE_RESTAURANTS, values, "_id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    // 🗑️ Eliminar restaurante
    public int deleteRestaurant(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(DatabaseHelper.TABLE_RESTAURANTS, "_id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    // 🍽️ Insertar platillo para restaurante
    public long insertDishForRestaurant(int restaurantId, Dish dish) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("restaurant_id", restaurantId);
        values.put("name", dish.name);
        values.put("price", dish.price);
        values.put("description", dish.description);
        values.put("type", dish.type);
        long newId = db.insert(DatabaseHelper.TABLE_DISHES, null, values);
        db.close();
        return newId;
    }

    // 🔍 Obtener platillos por tipo para un restaurante
    public List<Dish> getDishesByType(int restaurantId, String type) {
        List<Dish> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_DISHES,
                new String[]{"name", "price", "description", "type"},
                "restaurant_id = ? AND type = ?",
                new String[]{String.valueOf(restaurantId), type},
                null, null, null
        );

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String dishType = cursor.getString(cursor.getColumnIndexOrThrow("type"));

            list.add(new Dish(name, price, description, dishType));
        }

        cursor.close();
        db.close();
        return list;
    }
}