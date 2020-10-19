package com.example.fikisha.Repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.fikisha.Models.Cart;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OrdersRepository {
    MutableLiveData<ArrayList<Cart>> arrayListMutableLiveData = new MutableLiveData<>();

    public OrdersRepository(){
        final ArrayList<Cart> arrayList = new ArrayList<Cart>();
        Query query = FirebaseFirestore.getInstance().collection("Orders");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots ){
                    Log.d("OrdersrepoArralistData", documentSnapshot.toObject(Cart.class).toString());
                    arrayList.add(documentSnapshot.toObject(Cart.class));
                }
            }
        });
    }


    public MutableLiveData<ArrayList<Cart>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

}
