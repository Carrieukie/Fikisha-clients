package com.example.fikisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Menu;
import com.example.fikisha.RecyclerAdapter.FoodMenuAdapter;
import com.example.fikisha.Repository.MenuRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseFoodActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_food);
        recyclerView = findViewById(R.id.menuRecyclerView);
        FoodMenuAdapter foodMenuAdapter = new FoodMenuAdapter(this,getsortedData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(foodMenuAdapter);

    }

    ArrayList<Menu> getsortedData(){
        ArrayList<Menu> menuArrayList = new ArrayList<>();
        ArrayList<Food> foodArrayList = new MenuRepository().setupFood();
        HashSet<String> categories = new HashSet<>();

        for (Food food: foodArrayList) {
            categories.add(food.getCategory());
        }

        for (final String category :categories) {
            ArrayList<Food> foods = new ArrayList<>();

            for (Food food : foodArrayList) {
                if (food.getCategory().equals(category)) {
                    foods.add(food);
                }

            }
            menuArrayList.add(new Menu(category,foods));
        }
        return menuArrayList;
    }
}
