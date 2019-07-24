package com.example.papajohnsapp.Model;

public class BasketItem {
    public int basItemPicId;
    public String basItemTitle;
    public String basItemSize;
    public String basItemCount;
    public String basketItemPrice;
    public String basketItemPriceFix;

    public BasketItem(int basItemPicId, String basItemTitle, String basItemCount, String basketItemPrice, String basItemSize) {
        this.basItemPicId = basItemPicId;
        this.basItemTitle = basItemTitle;
        this.basItemSize = basItemSize;
        this.basItemCount = basItemCount;
        this.basketItemPrice = basketItemPrice;
        this.basketItemPriceFix = basketItemPrice;
    }

    public BasketItem(int basItemPicId, String basItemTitle, String basItemCount, String basketItemPrice) {
        this.basItemPicId = basItemPicId;
        this.basItemTitle = basItemTitle;
        this.basItemCount = basItemCount;
        this.basketItemPrice = basketItemPrice;
        this.basketItemPriceFix = basketItemPrice;
    }
}
