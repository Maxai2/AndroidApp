package com.example.papajohnsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryItem> categoryItems;

    CategoryBaseAdapter(Context context, ArrayList<CategoryItem> categoryItems) {
        this.context = context;
        this.categoryItems = categoryItems;
    }

    @Override
    public int getCount() {
        return categoryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.category_item, parent, false);

        ImageView pic = rowView.findViewById(R.id.pic);
        TextView text = rowView.findViewById(R.id.text);

        pic.setImageResource(categoryItems.get(position).categId);
        text.setText(categoryItems.get(position).categName);

        return rowView;
    }
}
