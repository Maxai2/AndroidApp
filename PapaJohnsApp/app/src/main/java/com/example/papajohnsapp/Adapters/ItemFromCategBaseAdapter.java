package com.example.papajohnsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.papajohnsapp.Model.CategoryCategItem;
import com.example.papajohnsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemFromCategBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryCategItem> categoryCategItems;

    public ItemFromCategBaseAdapter(Context context, ArrayList<CategoryCategItem> categoryCategItems) {
        this.context = context;
        this.categoryCategItems = categoryCategItems;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.categ_categ_item, parent, false);

        CardView categCategItem = rowView.findViewById(R.id.categCategItem);
        ImageView categPic = rowView.findViewById(R.id.categPic);
        TextView categName = rowView.findViewById(R.id.categName);
        TextView categIng = rowView.findViewById(R.id.categIng);

        categCategItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        categPic.setImageResource(categoryCategItems.get(position).CCIPic);
        categName.setText(categoryCategItems.get(position).CCIName);
        categIng.setText(categoryCategItems.get(position).CCIIngridients);

        return rowView;
    }
}
