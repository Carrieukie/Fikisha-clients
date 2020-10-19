package com.example.fikisha.OrdersFragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.Models.Cart;
import com.example.fikisha.R;
import com.example.fikisha.RecyclerAdapter.pendingOrdersAdapter;
import com.example.fikisha.ViewModel.OrdersViewModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Frag3 extends Fragment {

    RecyclerView recyclerView;
    View view;
    private pendingOrdersAdapter loadingOrdersAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerView_CompletedOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadingOrdersAdapter = new pendingOrdersAdapter();
        recyclerView.setAdapter(loadingOrdersAdapter);
//        OrdersViewModel ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
//        ordersViewModel.getOrders().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cart>>() {
//            @Override
//            public void onChanged(ArrayList<Cart> carts) {
//                ArrayList<Cart> cartArrayList = new ArrayList<>();
//
//                for (Cart cart : carts){
//                    if (cart.getApproved().equals("Completed")){
//                        cartArrayList.add(cart);
//                    }
//                }
//
//                loadingOrdersAdapter.setCartArrayList(cartArrayList);
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final ArrayList<Cart> arrayList = new ArrayList<Cart>();
        Query query = FirebaseFirestore.getInstance().collection("Orders");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots ){
                    Log.d("OrdersrepoArralistData", documentSnapshot.toObject(Cart.class).toString());
                    Cart cart = documentSnapshot.toObject(Cart.class);
                    if (cart.getApproved().equals("Completed")){
                        arrayList.add(cart);
                    }
                }
                loadingOrdersAdapter.setCartArrayList(arrayList);
            }
        });
    }

}
