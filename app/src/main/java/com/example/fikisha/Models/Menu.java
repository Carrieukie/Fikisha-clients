package com.example.fikisha.Models;

import java.util.ArrayList;

public class Menu {
    String title;
    ArrayList<Food> foodArrayList;

    public Menu(String title, ArrayList<Food> foodArrayList) {
        this.title = title;
        this.foodArrayList = foodArrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public void setFoodArrayList(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }
}
