package com.example.fikisha.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.Repository.MenuRepository;

import java.util.ArrayList;

public class MenuViewModel extends AndroidViewModel {

    private LiveData<ArrayList<Food>> liveData ;
    MenuRepository menuRepository = new MenuRepository();

    public MenuViewModel(@NonNull Application application) {
        super(application);
        liveData = menuRepository.getMutableLiveData();
    }

    public LiveData<ArrayList<Food>> getLiveData() {
        return liveData;
    }
}
