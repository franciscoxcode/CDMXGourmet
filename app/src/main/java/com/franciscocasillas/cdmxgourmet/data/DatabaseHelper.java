package com.franciscocasillas.cdmxgourmet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gourmet.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String TABLE_DISHES = "dishes"; // 🔄 antes "food"

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 🍽️ Tabla de restaurantes
        db.execSQL("CREATE TABLE " + TABLE_RESTAURANTS + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)");

        // 🍱 Tabla de platillos
        db.execSQL("CREATE TABLE " + TABLE_DISHES + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "description TEXT, " +
                "type TEXT CHECK(type IN ('food','drink','complement')) NOT NULL, " +
                "restaurant_id INTEGER, " +
                "FOREIGN KEY(restaurant_id) REFERENCES restaurants(_id))");

        // Datos de prueba
        db.execSQL("INSERT INTO restaurants (name) VALUES ('Tacos El Gato')");
        db.execSQL("INSERT INTO restaurants (name) VALUES ('Sushi Swift')");
        db.execSQL("INSERT INTO restaurants (name) VALUES ('Café Kotlin')");

        db.execSQL("INSERT INTO dishes (name, price, description, type, restaurant_id) " +
                "VALUES ('Taco al Pastor', 25.00, 'Con piña y salsa roja', 'food', 1)");
        db.execSQL("INSERT INTO dishes (name, price, description, type, restaurant_id) " +
                "VALUES ('Agua de Horchata', 15.00, 'Refrescante y dulce', 'drink', 1)");
        db.execSQL("INSERT INTO dishes (name, price, description, type, restaurant_id) " +
                "VALUES ('Guacamole', 30.00, 'Con totopos', 'complement', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        onCreate(db);
    }
}