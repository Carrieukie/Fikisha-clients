package com.example.fikisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.Models.Vendor;
import com.example.fikisha.RecyclerAdapter.VendorsAdapter;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

 ArrayList<Vendor> vendorArrayList;
 RecyclerView recyclerView;
 VendorsAdapter vendorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerView = findViewById(R.id.vendorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vendorsAdapter = new VendorsAdapter();
        vendorsAdapter.setVendors(getVendorArrayList());
        recyclerView.setAdapter(vendorsAdapter);
    }

    public ArrayList<Vendor> getVendorArrayList() {
        vendorArrayList = new ArrayList<>();
        vendorArrayList.add(new Vendor(R.drawable.tiryo,"Tiryo Grill"));
        return vendorArrayList;
    }



}
