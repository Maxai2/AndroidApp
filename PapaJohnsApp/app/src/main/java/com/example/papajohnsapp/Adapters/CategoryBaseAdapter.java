package com.example.papajohnsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.papajohnsapp.CategCategActivity;
import com.example.papajohnsapp.Model.CategoryItem;
import com.example.papajohnsapp.R;
import com.example.papajohnsapp.Interfaces.onItemClickListner;

import java.util.ArrayList;

public class CategoryBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryItem> categoryItems;

    onItemClickListner onItemClickListner;

    public void setOnItemClickListner(onItemClickListner onItemClickListner)
    {
        this.onItemClickListner = onItemClickListner;
    }

    public CategoryBaseAdapter(Context context, ArrayList<CategoryItem> categoryItems) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.category_item, parent, false);

        CardView cv = rowView.findViewById(R.id.categItem);
        ImageView pic = rowView.findViewById(R.id.pic);
        TextView text = rowView.findViewById(R.id.text);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategCategActivity.class);
                intent.putExtra("categName", categoryItems.get(position).categName);

                onItemClickListner.onItemClick(intent);
            }
        });

        pic.setImageResource(categoryItems.get(position).categId);
        text.setText(categoryItems.get(position).categName);

        return rowView;
    }
}
