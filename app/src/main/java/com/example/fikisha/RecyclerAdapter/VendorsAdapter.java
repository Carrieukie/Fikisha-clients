package com.example.fikisha.RecyclerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikisha.ChooseFoodActivity;
import com.example.fikisha.Models.Vendor;
import com.example.fikisha.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.ViewHolder> {

    ArrayList<Vendor> vendors;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Vendor vendor = vendors.get(position);
        holder.bind(vendor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.imageView.getContext(), ChooseFoodActivity.class);
                intent.putExtra("venddor Name",vendor.getName());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vendors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView vendorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vendorcircleImageView);
            vendorName = itemView.findViewById(R.id.vendorTextView);
        }
        void bind(Vendor vendor){
            imageView.setImageResource(vendor.getImage());
            vendorName.setText(vendor.getName());
        }

    }

    public void setVendors(ArrayList<Vendor> vendors) {
        this.vendors = vendors;
        notifyDataSetChanged();
    }
}
