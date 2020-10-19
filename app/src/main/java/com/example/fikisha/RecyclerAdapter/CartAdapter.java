package com.example.fikisha.RecyclerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.Models.Food;
import com.example.fikisha.Models.Order;
import com.example.fikisha.R;
import com.example.fikisha.SharedPrefs.CartManager;
import com.example.fikisha.ViewModel.CartLive;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ordersViewHolder> {

    ArrayList<Order> orders;

    public CartAdapter(){

    }

    @NonNull
    @Override
    public ordersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ordersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ordersViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
        holder.numberPicker.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                if (order instanceof Food){
                    ((Food) order).setQuantity(String.valueOf(value));
                    ((Food) order).setTotalPrice();
                    holder.mTo.setText("Ksh : " + ((Food) order).getTotalPrice());
                    updateNewTotal();
                }
            }
        });
    }

    void updateNewTotal(){
        int productTotal = 0;
        for (Order o :this.orders) {
            if (o instanceof Food){
                productTotal += Integer.valueOf(((Food) o).getTotalPrice());
            }
        }
        CartLive.productTotal.postValue(productTotal);
    }

    @Override
    public int getItemCount() {
        if (orders == null){
            return 0;
        }
        return orders.size();
    }

    public void refreshCart(ArrayList<Order> orders){
        this.orders = null;
        this.orders = orders;
        notifyDataSetChanged();
    }

    public class ordersViewHolder extends RecyclerView.ViewHolder {
        ImageView type;
        TextView orderType,orderDesc,mFrom,mTo;
        ScrollableNumberPicker numberPicker;

        public ordersViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.imageView_CartOrderImg);
            orderType = itemView.findViewById(R.id.textView_OrderType);
            orderDesc = itemView.findViewById(R.id.textView_OrderDesc);
            mFrom = itemView.findViewById(R.id.textView_From);
            mTo = itemView.findViewById(R.id.textView_To);
            numberPicker = itemView.findViewById(R.id.numberPicker);
        }



        public void bind(Order order){
            if (order instanceof Food){
                orderDesc.setText(((Food) order).getTitle());
                mFrom.setText("From : " + order.getFrom() + " (" + ((Food) order).getVendor() + ")");
                mTo.setText("Ksh : " + ((Food) order).getPrice());
                mTo.setTypeface(Typeface.DEFAULT_BOLD);
                mTo.setTextColor(Color.BLACK);

            }else {
                orderDesc.setText(order.getDescription());
                mFrom.setText("From : " + order.getFrom());
                mTo.setText("To : " + order.getTo());
                numberPicker.setVisibility(View.GONE);
            }
            orderType.setText(order.getType());

            switch (order.getType()){
                case "":
                    break;
                case "Food":
                    type.setImageResource(R.drawable.food);
                    numberPicker.setVisibility(View.VISIBLE);
                    break;
                case "Groceries":
                    type.setImageResource(R.drawable.groceries);
                    break;
                case "Luggage":
                    type.setImageResource(R.drawable.backpack);
                    break;
                case "Medicene":
                    type.setImageResource(R.drawable.medicene);
                    break;
                case "Parcel":
                    type.setImageResource(R.drawable.parcel);
                    break;
                case "Others":
                    type.setImageResource(R.drawable.others);
                    break;
            }
        }
    }
}
