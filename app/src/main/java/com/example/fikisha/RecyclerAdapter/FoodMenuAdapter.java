package com.example.fikisha.RecyclerAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.Models.Menu;
import com.example.fikisha.R;
import com.example.fikisha.ViewModel.CartViewModel;

import java.util.ArrayList;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.ViewHolder> {

    ArrayList<Menu> menuArrayList = new ArrayList<>();
    Activity context;

    public FoodMenuAdapter(Activity context, ArrayList<Menu> menus) {
        this.context = context;
        this.menuArrayList = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = menuArrayList.get(position);
        holder.bind(menu);
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        RecyclerView categoryRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            categoryRecyclerView = itemView.findViewById(R.id.categoryRecyclerView);
        }

        void bind(Menu menu){
            categoryTitle.setText(menu.getTitle());
            categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            categoryRecyclerView.setNestedScrollingEnabled(true);
            FooditemAdapter fooditemAdapter =new FooditemAdapter(ViewModelProviders.of((FragmentActivity) context).get(CartViewModel.class));
            fooditemAdapter.setFoodArrayList(menu.getFoodArrayList());
            categoryRecyclerView.setAdapter(fooditemAdapter);
        }
    }

    public void setMenuArrayList(ArrayList<Menu> menuArrayList) {
        this.menuArrayList = menuArrayList;
        notifyDataSetChanged();
    }
}
