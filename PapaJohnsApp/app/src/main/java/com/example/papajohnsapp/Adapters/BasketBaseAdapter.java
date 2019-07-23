package com.example.papajohnsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.papajohnsapp.Model.BasketItem;
import com.example.papajohnsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BasketBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<BasketItem> basketItems = new ArrayList<>();

    public BasketBaseAdapter(Context context, ArrayList<BasketItem> basketItems) {
        this.context = context;
        this.basketItems = basketItems;
    }

    @Override
    public int getCount() {
        return basketItems.size();
    }

    @Override
    public Object getItem(int position) {
        return basketItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.basket_item, parent, false);

        ImageView basketPic = rowView.findViewById(R.id.basketPic);
        TextView basketItemTitle = rowView.findViewById(R.id.basketItemTitle);
        TextView basketItemSize = rowView.findViewById(R.id.basketItemSize);
        TextView itemCount = rowView.findViewById(R.id.itemCount);

        basketPic.setImageResource(basketItems.get(position).basItemPicId);
        basketItemTitle.setText(basketItems.get(position).basItemTitle);

        String sizeTemp = basketItems.get(position).basItemSize;
        if (sizeTemp.equals("")) {
            basketItemSize.setVisibility(View.GONE);
        } else {
            basketItemSize.setText(sizeTemp);
        }

        itemCount.setText(basketItems.get(position).basItemCount);

        return null;
    }
}
