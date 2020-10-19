package com.example.fikisha.Models;

import com.example.fikisha.Util.DateUtil;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    String date;
    String time;
    String phoneNumber;
    String items;
    String paid;
    String approved;
    String courier;
    String costs;
    String courierpphone;
    ArrayList<Order> orders;
    ArrayList<Food> foodArrayListorders;

    public Cart() {
    }

    public Cart(ArrayList<Order> orders, ArrayList<Food> foodArrayListorders) {
        this.date = DateUtil.dateformatter(new Date());
        this.time = String.valueOf(new Date().getTime());
        this.phoneNumber = setPhoneNumber();
        this.paid = "not paid";
        this.courier = "Pending";
        this.costs = "Pending";
        this.orders = orders;
        this.foodArrayListorders = foodArrayListorders;
        this.approved = "false";
        this.courierpphone = "+254795546364";
        this.items = "";
        setItems();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setItems(String items) {
        this.items = items;
    }
    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getCourierpphone() {
        return courierpphone;
    }

    public void setCourierpphone(String courierpphone) {
        this.courierpphone = courierpphone;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String setPhoneNumber() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
           return this.phoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        }
        return "";
    }

    public String getItems() {
        return items;
    }

    public void setItems() {
        int orders = (getOrders() == null) ? 0 : getOrders().size();
        int food = (getFoodArrayListorders() == null) ? 0 : getFoodArrayListorders().size();
        this.items = String.valueOf( food + orders );
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Food> getFoodArrayListorders() {
        return foodArrayListorders;
    }

    public void setFoodArrayListorders(ArrayList<Food> foodArrayListorders) {
        this.foodArrayListorders = foodArrayListorders;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", items='" + items + '\'' +
                ", paid='" + paid + '\'' +
                ", orders=" + orders +
                ", approved='" + approved + '\'' +
                ", courier='" + courier + '\'' +
                ", costs='" + costs + '\'' +
                ", courierpphone='" + courierpphone + '\'' +
                '}';
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        for (Order order: getOrders()) {
            orders.add(order);
        }

        for (Order order : getFoodArrayListorders()){
            orders.add(order);
        }

        return orders;
    }
}
