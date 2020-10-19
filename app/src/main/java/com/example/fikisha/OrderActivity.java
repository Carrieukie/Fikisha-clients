package com.example.fikisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fikisha.Models.Order;
import com.example.fikisha.Repository.CartRepository;
import com.example.fikisha.SharedPrefs.CartManager;
import com.example.fikisha.ViewModel.CartViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class OrderActivity extends AppCompatActivity {

    TextInputLayout from, to , description;
    LinearLayout linearLayout;

    ImageView imageView;
    TextView textView;
    String service;

    void animate(){
        linearLayout = findViewById(R.id.LinearLayout);
        linearLayout.startAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        from = findViewById(R.id.textInputLayoutFrom);
        to = findViewById(R.id.textInputLayoutTo);
        description = findViewById(R.id.textInputLayoutDesc);

        imageView = findViewById(R.id.imageView_Order);
        textView = findViewById(R.id.textView_Order);

        Intent intent = getIntent();
        int image = intent.getIntExtra("image",R.drawable.others);
        service = intent.getStringExtra("service");
        imageView.setImageResource(image);
        textView.setText(service);
        animate();
    }



    public void addcart(View view) {
        String mfrom = from.getEditText().getText().toString();
        String mto = to.getEditText().getText().toString();
        String desc = description.getEditText().getText().toString();
        String mtype = service;
        Order order = new Order(mtype,mfrom,mto,desc);
        CartViewModel viewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        viewModel.AddCartValue(order);
        startActivity(new Intent(getApplicationContext(),CartActivity.class));
        finish();
    }
}
