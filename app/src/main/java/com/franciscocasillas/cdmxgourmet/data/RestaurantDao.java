package com.franciscocasillas.cdmxgourmet.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.franciscocasillas.cdmxgourmet.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {
    private final DatabaseHelper dbHelper;

    public RestaurantDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "restaurants",
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
            restaurant.id = cursor.getInt(cursor.getColumnIndexOrThrow("_id")); // asegúrate de tener esta propiedad
            restaurantList.add(restaurant);
        }

        cursor.close();
        db.close();
        return restaurantList;
    }

    public long insertRestaurant(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        long newId = db.insert("restaurants", null, values);
        db.close();
        return newId;
    }

    public int updateRestaurant(int id, String newName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        int rows = db.update("restaurants", values, "_id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public int deleteRestaurant(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("restaurants", "_id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}