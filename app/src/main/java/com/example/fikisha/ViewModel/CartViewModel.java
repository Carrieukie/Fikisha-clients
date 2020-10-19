package com.example.fikisha.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.Repository.CartRepository;

import java.util.ArrayList;

public class CartViewModel extends AndroidViewModel {

    private static LiveData<ArrayList<Order>> allOrderArrayList;
    private static LiveData<ArrayList<Order>> OrderArrayList;
    private static LiveData<ArrayList<Food>> foodArrayList;

    private CartRepository mRepository;
    Context context;

    public CartViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CartRepository(application);
        context = application;
    }

    public  LiveData<ArrayList<Order>> getAllOrderArrayList() {
        return mRepository.getAllCartObjects();
    }


    public  LiveData<ArrayList<Order>> getOrderArrayList() {
        OrderArrayList = mRepository.getOrderArrayList();
        return OrderArrayList;
    }



    public  LiveData<ArrayList<Food>> getFoodArrayList() {
        foodArrayList = mRepository.getFoodArrayList();
        return foodArrayList;
    }


    public void AddCartValue (Order order){
        mRepository.AddCartValue(order);
    }

    public void removeCartValue(Order remove){
        mRepository.removeOrderFromCart(remove);
    }

    public void returnOrder(int index, Order order){
        mRepository.returnCartValue(index,order);
    }

    public void removeallOrders(){
        mRepository.removeAll();
    }

}
