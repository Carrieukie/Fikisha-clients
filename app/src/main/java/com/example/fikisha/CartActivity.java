package com.example.fikisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fikisha.Models.Food;
import com.example.fikisha.OrdersFragments.MyOrdersActivity;
import com.example.fikisha.Models.Cart;
import com.example.fikisha.Models.Order;
import com.example.fikisha.RecyclerAdapter.CartAdapter;
import com.example.fikisha.ViewModel.CartLive;
import com.example.fikisha.ViewModel.CartViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";


    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    CartViewModel cartViewModel;
    TextView product, shipping, total;
    ScrollView hasOrders;
    ConstraintLayout hasNoOrders;
    TextInputEditText shippingLocation;

    private ArrayList<Order> orderss;

    private int productTotal = 0, shippingTotal = 0, ultimateTotalPrice = 0;
    private boolean fromKapsabetHasNotBeenAdded = true, fromBracHasNotBeenAdded = true;

    NotificationManager mNotifyManager;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        product = findViewById(R.id.product_total);
        shipping = findViewById(R.id.shipping_Cost);
        total = findViewById(R.id.total_Cost);
        hasOrders = findViewById(R.id.layout_hasOrders);
        hasNoOrders = findViewById(R.id.layout_hasNoOrders);
        shippingLocation = findViewById(R.id.shippping_location);

        cartAdapter = new CartAdapter();
        recyclerView = findViewById(R.id.recyclerView_Cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cartAdapter);

        cartViewModel = new ViewModelProvider(CartActivity.this).get(CartViewModel.class);
        cartViewModel.getAllOrderArrayList().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                if (orders.size() == 0){
                    hasOrders.setVisibility(View.GONE);
                    hasNoOrders.setVisibility(View.VISIBLE);
                }else {
                    hasOrders.setVisibility(View.VISIBLE);
                    hasNoOrders.setVisibility(View.GONE);
                    cartAdapter.refreshCart(orders);
                    orderss = orders;
                    displayCosts();
                }

            }
        });

        CartLive.productTotal.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                product.setText("Ksh : " +  integer);
                total.setText("Ksh : " + (integer + shippingTotal));
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Order order = cartViewModel.getAllOrderArrayList().getValue().get(position);
                cartViewModel.removeCartValue(order);
                Snackbar.make(recyclerView, "Order to " + order.getFrom() + " deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        cartViewModel.returnOrder(position,order);
                                        Snackbar.make(recyclerView,"Delete Undone ",Snackbar.LENGTH_SHORT)
                                                .setAction("\uD83D\uDC7B", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                    }
                                                })
                                                .show();
                                    }
                                }
                        ).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void calculateCosts(){
        productTotal = 0; shippingTotal = 0; ultimateTotalPrice = 0;
        fromKapsabetHasNotBeenAdded = true; fromBracHasNotBeenAdded = true;

        if (orderss == null ){
            return;
        }

        for (Order order: orderss){
            if (order instanceof Food){
                productTotal += Integer.parseInt(((Food) order).getTotalPrice());
            }

            if (order.getFrom().equals("Kapsabet")){
                if (fromKapsabetHasNotBeenAdded){
                    shippingTotal += 90;
                    fromKapsabetHasNotBeenAdded = false;
                }
            }else {
                if (fromBracHasNotBeenAdded){
                    shippingTotal += 30;
                    fromBracHasNotBeenAdded = false;
                }
            }

        }
        ultimateTotalPrice = productTotal + shippingTotal;

    }

    public void displayCosts() {
        calculateCosts();
        CartLive.productTotal.postValue(productTotal);
        product.setText("Ksh. " + productTotal);
        shipping.setText("Ksh. " + shippingTotal);
        total.setText("Ksh. " + ultimateTotalPrice);
    }

    public void sendcart(View view) {
        if (TextUtils.isEmpty(shippingLocation.getText())){
            shippingLocation.setError("Please specify where You want them delivered.");
            return;
        }
        final ProgressBar progressBar = findViewById(R.id.progressBar_uploading);
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<Order> orders = cartViewModel.getOrderArrayList().getValue();
        ArrayList<Food> foodArrayList = cartViewModel.getFoodArrayList().getValue();
        Cart cart = new Cart(orders,foodArrayList);
        cart.setCosts("Ksh. " + ultimateTotalPrice);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Orders").add(cart).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                cartViewModel.removeallOrders();
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(), MyOrdersActivity.class));
                makeNotification();
                finish();
            }
        });
    }

    private void makeNotification(){

        createNotificationChannel();

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, PRIMARY_CHANNEL_ID)
                .setContentTitle("New Order")
                .setContentText("A new Order has been Made")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_fikishaicon)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotifyManager.notify(0, builder.build());
    }
    public void createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Job Service notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifications from Job Service");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

//
//    public void makeorders(View view) {
//        finish();
//    }
}
