package com.example.fikisha.Models;

public class User {

    String image, name, phoneNumber, id;

    public User() {
    }

    public User(String image, String name, String phoneNumber, String id) {
        this.image = image;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
