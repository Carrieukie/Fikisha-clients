package com.example.fikisha.Models;

public class services{
    int image;
    String service;

    public services(int image, String service) {
        this.image = image;
        this.service = service;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
