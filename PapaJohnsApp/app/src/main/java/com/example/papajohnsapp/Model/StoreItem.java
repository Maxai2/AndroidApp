package com.example.papajohnsapp.Model;

public class StoreItem {
    int storePicId;
    String storeName;
    String storeStreet;
    String storeWorkTime;

    public StoreItem(int storePicId, String storeName, String storeStreet, String storeWorkTime) {
        this.storePicId = storePicId;
        this.storeName = storeName;
        this.storeStreet = storeStreet;
        this.storeWorkTime = storeWorkTime;
    }
}
