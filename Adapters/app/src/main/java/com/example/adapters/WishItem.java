package com.example.adapters;

public class WishItem {
    int img;
    String title;
    Double price;
    boolean isWish;

    public WishItem(int img, String title, Double price) {
        this.img = img;
        this.title = title;
        this.price = price;
        isWish = false;
    }
}
