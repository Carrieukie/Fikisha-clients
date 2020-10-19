package com.example.fikisha.RecyclerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.CartViewActivity;
import com.example.fikisha.Models.Cart;
import com.example.fikisha.Models.Order;
import com.example.fikisha.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class pendingOrdersAdapter extends RecyclerView.Adapter<pendingOrdersAdapter.ViewHolder> {

    ArrayList<Cart> cartArrayList = new ArrayList<>();
    public pendingOrdersAdapter(){
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Cart cart = cartArrayList.get(position);
        holder.bind(cart);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obj = new Gson().toJson(cart);
                Intent intent = new Intent(holder.itemView.getContext(), CartViewActivity.class);
                intent.putExtra("Cart",obj);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cartArrayList == null){
            return 0;
        }
        return cartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,items,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textView_cartsDate);
            items = itemView.findViewById(R.id.textView_Cartsitems);
            time = itemView.findViewById(R.id.textView_CartOrderedtime);
        }

        public void bind(Cart cart){
            date.setText(cart.getDate());
            time.setText(getTime(cart.getTime()));
            String item = "";
            String Item = "";
            for (Order order: cart.getAllOrders()){
                item += order.getType() + ",";
            }
            item = item.replaceAll(",$","");
            Item = cart.getItems().equals("1") ? " Item " : " Items ";
            items.setText(cart.getItems()  +Item + "{" + item + "}");
        }

        public String getTime(String timeStamp){
            Date date = new Date(Long.valueOf(timeStamp));
            DateFormat formatter = new SimpleDateFormat("hh:mm aa");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dateFormatted = formatter.format(date);
            return dateFormatted;
        }
    }

    public void setCartArrayList(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
        notifyDataSetChanged();
    }

}
