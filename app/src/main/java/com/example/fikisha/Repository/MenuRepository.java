package com.example.fikisha.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.Models.Vendor;

import java.util.ArrayList;

public class MenuRepository {

    MutableLiveData<ArrayList<Food>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Food>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData() {
        this.mutableLiveData.postValue(setupFood());
    }

    public ArrayList<Food> setupFood(){
       ArrayList<Food> arrayList = new ArrayList<>();
       arrayList.add(new Food("Chips plain","120","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Chips Masala","150","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Poussin Chips","200","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Bhajia","150","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Kebab","60","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("sausage","50","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Ndazi","20","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Samosa - Beef","40","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Samosa - Vegetable","30","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Chapati(White or Brown)","30","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Pancake","60","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Spanish Omelette (Pair)","100","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Plain Omelette (Pair)","60","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Boiled Egg(Pair)","60","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Scrambled eggs","60","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Sweet Potato","30","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Arrowroot","30","Tiryo Grill","Chips & Snacks"));

       arrayList.add(new Food("Beef Wet Fry & Chapati/Rice/Ugali","230","Tiryo Grill","Beef Dishes"));
       arrayList.add(new Food("Beef Dry Fry & Chapati/Rice/Ugali","230","Tiryo Grill","Beef Dishes"));
       arrayList.add(new Food("Beef Stew & Chapati/Rice/Ugali","230","Tiryo Grill","Beef Dishes"));
       arrayList.add(new Food("Beef Fillet Dry Fry & Chapati/Rice/Ugali","260","Tiryo Grill","Beef Dishes"));
       arrayList.add(new Food("Beef pilau","250","Tiryo Grill","Beef Dishes"));

       arrayList.add(new Food("Goat/Liver Wet Fry & Chapati/Rice/Ugali","280","Tiryo Grill","Goat & Liver Dishes"));
       arrayList.add(new Food("Goat/Liver Dry Fry & Chapati/Rice/Ugali","280","Tiryo Grill","Goat & Liver Dishes"));
       arrayList.add(new Food("Goat/Liver Stew & Chapati/Rice/Ugali","280","Tiryo Grill","Goat & Liver Dishes"));
       arrayList.add(new Food("Goat Pilau","300","Tiryo Grill","Goat & Liver Dishes"));

       arrayList.add(new Food("Chicken Wet Fry & Chapati/Rice/Ugali","250","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Chicken Dry Fry & Chapati/Rice/Ugali","250","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Chicken Stew Fry & Chapati/Rice/Ugali","250","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Chicken Pilau","300","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Chilli Chicken","200","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Chicken Lollipop","220","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("1/4 Chicken Chips","350","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("Kuku Kienyeji & Chapati/Rice/Ugali","340","Tiryo Grill","Chicken Dishes"));
       arrayList.add(new Food("1/4 Kuku Kienyeji & Chips","410","Tiryo Grill","Chicken Dishes"));

       arrayList.add(new Food("Fish Wet Fry & Chapati/Rice/Ugali","320","Tiryo Grill","Fish Dishes"));
       arrayList.add(new Food("Fish Dry Fry & Chapati/Rice/Ugali","320","Tiryo Grill","Fish Dishes"));
       arrayList.add(new Food("Fish Masala Fry & Chapati/Rice/Ugali","340","Tiryo Grill","Fish Dishes"));
       arrayList.add(new Food("Fish Fillet & Chips","390","Tiryo Grill","Fish Dishes"));
       arrayList.add(new Food("Fish Masala & Chips","410","Tiryo Grill","Fish Dishes"));

       arrayList.add(new Food("Managu & Chapati/Rice/Ugali","150","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Managu Special & Chapati/Rice/Ugali","200","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Beans & Chapati/Rice/Ugali","130","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Green Grams Stew & Chapati/Rice","150","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Peas Carrot Stew & Chapati/Rice","150","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Vegetable Rice","180","Tiryo Grill","Vegetable Dishes"));
       arrayList.add(new Food("Vegetable Pilau","200","Tiryo Grill","Vegetable Dishes"));

       arrayList.add(new Food("Steamed Rice Plain","100","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Beans Plain","80","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Managu plain","100","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Managu Special Plain","150","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Green Grams (Dengu) Plain","100","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("1/4 Chicken Broiler Plain","230","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("1/4 Chicken Kienyeji Plain","290","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Beef Plain","200","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Goat Plain","230","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Liver Plain","230","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Fish Fillet","250","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Fish Masala Plain","290","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Peas Plain","100","Tiryo Grill","Chips & Snacks"));
       arrayList.add(new Food("Pilau plain","150","Tiryo Grill","Chips & Snacks"));

       return arrayList;
   }
}
