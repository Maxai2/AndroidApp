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

import com.example.papajohnsapp.ItemDetailActivity;
import com.example.papajohnsapp.Model.CategoryCategItem;
import com.example.papajohnsapp.R;
import com.example.papajohnsapp.Interfaces.onItemClickListner;
import com.google.gson.Gson;

import java.util.ArrayList;


public class ItemFromCategBaseAdapter extends BaseAdapter {

   onItemClickListner onItemClickListner;

    Context context;
    ArrayList<CategoryCategItem> categoryCategItems;
    String categ;

    public void setOnItemClickListner(onItemClickListner onItemClickListner)
    {
        this.onItemClickListner = onItemClickListner;
    }

    public ItemFromCategBaseAdapter(Context context, ArrayList<CategoryCategItem> categoryCategItems, String categ) {
        this.context = context;
        this.categoryCategItems = categoryCategItems;
        this.categ = categ;
    }

    @Override
    public int getCount() {
        return categoryCategItems.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryCategItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.categ_categ_item, parent, false);

        CardView categCategItem = rowView.findViewById(R.id.categCategItem);
        ImageView categPic = rowView.findViewById(R.id.categPic);
        TextView categName = rowView.findViewById(R.id.categName);
        TextView categIng = rowView.findViewById(R.id.categIng);

        categCategItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra("itemCateg", categ);
                intent.putExtra("itemName", new Gson().toJson(categoryCategItems.get(position)));
                //context.startActivity(intent);

                onItemClickListner.onItemClick(intent);
            }
        });

        categPic.setImageResource(categoryCategItems.get(position).CCIPic);
        categName.setText(categoryCategItems.get(position).CCIName);
        categIng.setText(categoryCategItems.get(position).CCIIngridients);

        return rowView;
    }
}
