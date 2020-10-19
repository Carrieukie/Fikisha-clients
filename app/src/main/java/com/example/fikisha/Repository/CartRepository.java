package com.example.fikisha.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.SharedPrefs.CartManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    private static CartManager cartManager;
    private static MutableLiveData<ArrayList<Order>> allOrderArrayList;

    private static MutableLiveData<ArrayList<Order>> orderArrayList;
    private static MutableLiveData<ArrayList<Food>> foodArrayList;

    private static Gson gson = new Gson();
    private static Type orderType = new TypeToken<List<Order>>() {}.getType();
    private static Type foodType = new TypeToken<List<Food>>() {}.getType();

    static Context context;

    public CartRepository(Context context) {
        if (cartManager == null){
            cartManager = CartManager.getCartManagerInstance();
        }
        if (allOrderArrayList == null){
            allOrderArrayList = new MutableLiveData<>();
        }

        if (orderArrayList == null){
            orderArrayList = new MutableLiveData<>();
        }

        if (foodArrayList == null){
            foodArrayList = new MutableLiveData<>();
        }

        this.context = context;
    }

    public  MutableLiveData<ArrayList<Order>> getAllCartObjects() {

        ArrayList<Order> orders = getCartOrders();
        ArrayList<Food> foodorders = getFoodOrders();


        if (foodorders != null){
            orders.addAll(foodorders);
        }

        allOrderArrayList.postValue(orders);
        return allOrderArrayList;
    }

    public  MutableLiveData<ArrayList<Order>> getOrderArrayList() {
        orderArrayList.postValue(getCartOrders());
        return orderArrayList;
    }

    public  MutableLiveData<ArrayList<Food>> getFoodArrayList() {
        foodArrayList.postValue(getFoodOrders());
        return foodArrayList;
    }

    public static ArrayList<Order> getCartOrders() {
        String jsonorders = cartManager.getSharedPreferences(context).getString(CartManager.CART_ORDERS , null);

        ArrayList<Order> orders = gson.fromJson(jsonorders,orderType);
        return orders;
    }

    public static ArrayList<Food> getFoodOrders() {

        String jsonfoods = cartManager.getSharedPreferences(context).getString(CartManager.FOOD_ORDERS , null);

        ArrayList<Food> foodorders = gson.fromJson(jsonfoods,foodType);
        foodArrayList.postValue(foodorders);
        return foodorders;
    }

    public void AddCartValue(Order order) {


        ArrayList<Order> orders = getCartOrders();
        ArrayList<Food> foods = getFoodOrders();

        if (orders == null){
            orders = new ArrayList<>();
        };

        if (foods == null){
            foods = new ArrayList<>();
        }

        if (order instanceof Food){
            order.setFoodNod(foods);
            foods.add((Food) order);
        }else {
            order.setOrderNo(orders);
            orders.add(order);
        }


        cartManager.saveOrder(context,orders);
        cartManager.saveFood(context,foods);
        ArrayList<Order> all= new ArrayList<>();
        all.addAll(foods);
        all.addAll(orders);
        allOrderArrayList.postValue(all);
        foodArrayList.postValue(foods);
        orderArrayList.postValue(orders);
    }

    public  void removeOrderFromCart(Order removed){

        int orderNumber = removed.getCartNumber();
        ArrayList<Food> foodOrders = getFoodOrders();
        ArrayList<Order> orders = getCartOrders();

        if (removed instanceof Food){

            for (int i = 0; i < foodOrders.size(); i++) {
                Order order = foodOrders.get(i);
                if (order.getCartNumber() == orderNumber){
                    foodOrders.remove(order);
                }
            }
            cartManager.saveFood(context, foodOrders);
        }else {


            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                if (order.getCartNumber() == orderNumber){
                    orders.remove(order);
                }
            }
            cartManager.saveOrder(context, orders);
        }
        orders.addAll(foodOrders);
        allOrderArrayList.postValue(orders);

    }

    public void returnCartValue(int index, Order order) {

        ArrayList<Food> foodOrders = getFoodOrders();
        ArrayList<Order> orders = getCartOrders();

        if (order instanceof  Food){
            if (foodOrders == null){
                foodOrders = new ArrayList<>();
            }

            foodOrders.add(index, (Food) order);
            foodArrayList.postValue(foodOrders);
            cartManager.saveFood(context,foodOrders);
        }
        else {
            if (orders == null){
                orders = new ArrayList<>();
            };
            orders.add(index,order);
            orderArrayList.postValue(orders);
            cartManager.saveOrder(context,orders);
        }
        ArrayList<Order> all = new ArrayList<>();
        all.addAll(orders);
        all.addAll(foodOrders);
      allOrderArrayList.postValue(all);

    }

    public void removeAll() {
        allOrderArrayList.postValue(null);
        foodArrayList.postValue(null);
        orderArrayList.postValue(null);
        cartManager.clear(context);
    }
}
