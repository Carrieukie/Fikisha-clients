package com.example.fikisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fikisha.Models.Cart;
import com.example.fikisha.RecyclerAdapter.FooditemAdapter;
import com.google.gson.Gson;

public class CartViewActivity extends AppCompatActivity {
    Cart cart;
    TextView processing, courier, cost, paid;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordered_cart_items);

        recyclerView = findViewById(R.id.recyclerView_items);
        processing = findViewById(R.id.textView_processing);
        courier = findViewById(R.id.textView_Courier);
        cost = findViewById(R.id.textView_cost);
        paid = findViewById(R.id.textView_paid);

        Intent intent = getIntent();
        if(intent != null){
            cart = new Gson().fromJson(intent.getStringExtra("Cart"),Cart.class);
            bind(cart);
        }

    }

    private void bind(Cart cart) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FooditemAdapter fooditemAdapter = new FooditemAdapter(null);
        recyclerView.setAdapter(fooditemAdapter);
        fooditemAdapter.setFoodArrayList(cart.getFoodArrayListorders());

        processing.setText(cart.getApproved());
        courier.setText(cart.getCourier());
        cost.setText(cart.getCosts());
        paid.setText(cart.getPaid());
    }
}
