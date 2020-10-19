package com.example.fikisha.RecyclerAdapter;


import android.app.Activity;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.FoodActivity;
import com.example.fikisha.Models.services;
import com.example.fikisha.OrderActivity;
import com.example.fikisha.OrdersFragments.MyOrdersActivity;
import com.example.fikisha.R;

import java.util.ArrayList;


public class mainActivityAdapter  extends RecyclerView.Adapter<mainActivityAdapter.Viewholder> {

    ArrayList<services> servicesArrayList = new ArrayList();

    public mainActivityAdapter() {
        servicesArrayList.add(new services(R.drawable.food,"Food"));
        servicesArrayList.add(new services(R.drawable.groceries,"Groceries"));
        servicesArrayList.add(new services(R.drawable.backpack,"Luggage"));
        servicesArrayList.add(new services(R.drawable.medicene,"Medicene"));
        servicesArrayList.add(new services(R.drawable.parcel,"Parcel"));
        servicesArrayList.add(new services(R.drawable.others,"Others"));
        servicesArrayList.add(new services(R.drawable.my_orders,"My Orders"));
    }

    @NonNull
    @Override
    public mainActivityAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.services_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final mainActivityAdapter.Viewholder holder, int position) {
            final services services = servicesArrayList.get(position);
            holder.imageView.setImageResource(services.getImage());
            holder.textView.setText(services.getService());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.textView.getText().equals("My Orders")){
                        holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), MyOrdersActivity.class));
                    }else if(holder.textView.getText().equals("Food")){
                        holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), FoodActivity.class));
                    } else  {
                        Pair[] pair = new Pair[2];
                        pair[0] = new Pair<View, String>(holder.imageView,"imageMain");
                        pair[1] = new Pair<View, String>(holder.textView,"textMain");
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.imageView.getContext(),pair);
                        Intent intent = new Intent(holder.imageView.getContext(), OrderActivity.class);
                        intent.putExtra("image", services.getImage());
                        intent.putExtra("service", services.getService());
                        holder.itemView.getContext().startActivity(intent,activityOptionsCompat.toBundle());
                    }

                }
            });
    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_Orders);
            textView = itemView.findViewById(R.id.textView_Servicename);
        }
    }
}

