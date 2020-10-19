package com.example.fikisha.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    public static final String cart = "Cart_Values";
    public static final String CART_ORDERS = "CARTORDERS";
    public static final String FOOD_ORDERS = "FOODORDERS";

    public static CartManager cartManager= null;

    private static Gson gson = new Gson();

    private CartManager() {}

    public static CartManager getCartManagerInstance(){
        if (cartManager == null){
            cartManager = new CartManager();
        }
        return cartManager;
    }

    public  SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(cart, Context.MODE_PRIVATE);
    }

    public  void saveFood(Context context, ArrayList<Food> foods) {
        String newCartValue = gson.toJson(foods);
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(FOOD_ORDERS, newCartValue);
        editor.apply();
    }

    public  void saveOrder(Context context, ArrayList<Order> orders) {
        String newCartValue = gson.toJson(orders);
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CART_ORDERS, newCartValue);
        editor.apply();
    }

    public void clear(Context context){
        getSharedPreferences(context).edit().clear().apply();
    }
}
