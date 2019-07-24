package com.example.papajohnsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.papajohnsapp.Model.StoreItem;
import com.example.papajohnsapp.R;

import java.util.ArrayList;

public class StoreBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<StoreItem> storeItems = new ArrayList<>();

    StoreBaseAdapter(Context context, ArrayList<StoreItem> storeItems) {
        this.context = context;
        this.storeItems = storeItems;
    }

    @Override
    public int getCount() {
        return storeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return storeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.basket_item, parent, false);

        return null;
    }
}
