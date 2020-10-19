package com.example.fikisha.Models;


import com.example.fikisha.Util.DateUtil;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String id;
    private String type;
    private String from;
    private String to;
    private String description;
    private String date;
    private String time;
    private String delivered;
    private String paid;
    private int cartNumber;


    public Order(String type ,String from, String to, String description) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.description = description;
        init();
    }

    public Order(String id, String type, String from, String to, String description, String date, String time, String delivered, String paid) {
        this.id = id;
        this.type = type;
        this.from = from;
        this.to = to;
        this.description = description;
        this.date = date;
        this.time = time;
        this.delivered = delivered;
        this.paid = paid;
    }

    public void init(){
        this.date = DateUtil.dateformatter(new Date());
        this.time = String.valueOf(new Date().getTime());
        this.delivered = "false";
        this.paid = paid;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public Order() {

    }

    public int getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(int cartNumber) {
        this.cartNumber = cartNumber;
    }

    public void setOrderNo(ArrayList<Order> orders) {
        int num = 0;
        if (orders == null){
            this.cartNumber = 0;
            return;
        }
        for (Order order : orders) {
            if (order.getCartNumber() == 0){
                this.cartNumber = 1;
                return;
            }

            if (num < order.cartNumber){
                num = order.getCartNumber();
            }
        }
        this.cartNumber = num + 1;
    }

    public void setFoodNod(ArrayList<Food> foods) {
        int num = 0;
        if (foods == null){
            this.cartNumber = 0;
            return;
        }
        for (Order order : foods) {
            if (order.getCartNumber() == 0){
                this.cartNumber = 1;
                return;
            }

            if (num < order.cartNumber){
                num = order.getCartNumber();
            }
        }
        this.cartNumber = num + 1;
    }
}
