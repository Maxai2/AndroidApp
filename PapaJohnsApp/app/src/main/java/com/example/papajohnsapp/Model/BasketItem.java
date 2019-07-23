package com.example.papajohnsapp.Model;

public class BasketItem {
    public int basItemPicId;
    public String basItemTitle;
    public String basItemSize;
    public String basItemCount;

    public BasketItem(int basItemPicId, String basItemTitle, String basItemSize, String basItemCount) {
        this.basItemPicId = basItemPicId;
        this.basItemTitle = basItemTitle;
        this.basItemSize = basItemSize;
        this.basItemCount = basItemCount;
    }

    public BasketItem(int basItemPicId, String basItemTitle, String basItemCount) {
        this.basItemPicId = basItemPicId;
        this.basItemTitle = basItemTitle;
        this.basItemCount = basItemCount;
    }
}
