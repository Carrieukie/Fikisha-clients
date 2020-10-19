package com.example.fikisha.RecyclerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.CartActivity;
import com.example.fikisha.Models.Food;
import com.example.fikisha.R;
import com.example.fikisha.ViewModel.CartViewModel;

import java.util.ArrayList;

public class FooditemAdapter extends RecyclerView.Adapter<FooditemAdapter.ViewHolder> {

    private ArrayList<Food> foodArrayList = new ArrayList<>();
    private CartViewModel cartViewModel ;

    public FooditemAdapter(CartViewModel cartViewModel) {
        this.cartViewModel = cartViewModel;
    }

    @NonNull
    @Override
    public FooditemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.foodmenu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FooditemAdapter.ViewHolder holder, int position) {
        holder.bind(foodArrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartViewModel!=null){
                    cartViewModel.AddCartValue(foodArrayList.get(position));
                }
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), CartActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.shippingCost);
            foodPrice = itemView.findViewById(R.id.product_total);
        }

        void bind(Food food){
            foodName.setText(food.getTitle());
            foodPrice.setText("Ksh. " + food.getPrice());
        }
    }

    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public void setFoodArrayList(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
        notifyDataSetChanged();
    }

}
