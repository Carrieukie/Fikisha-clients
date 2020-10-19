package com.example.fikisha.Models;

import androidx.databinding.ObservableInt;

import java.util.Observable;

public class Food  extends Order{

    private String title;
    private String price;
    private String vendor;
    private String quantity;
    private String totalPrice;
    private String category;

    public Food() {
    }

    public Food(String title, String price, String vendor, String category) {

        super("Food", "Kapsabet", "", "");
        this.title = title;
        this.price = price;
        this.quantity = "1";
        setTotalPrice();
        this.vendor = vendor;
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice =String.valueOf(Integer.valueOf(quantity) * Integer.valueOf(price));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
