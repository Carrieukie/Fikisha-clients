package com.example.fikisha.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fikisha.Models.Cart;
import com.example.fikisha.Repository.OrdersRepository;

import java.util.ArrayList;

public class OrdersViewModel extends AndroidViewModel {
    LiveData<ArrayList<Cart>> listLiveData ;
    OrdersRepository ordersRepository = new OrdersRepository();

    public OrdersViewModel(@NonNull Application application) {
        super(application);
        listLiveData = ordersRepository.getArrayListMutableLiveData();
    }

    public LiveData<ArrayList<Cart>> getOrders(){
        return listLiveData;
    }


}
