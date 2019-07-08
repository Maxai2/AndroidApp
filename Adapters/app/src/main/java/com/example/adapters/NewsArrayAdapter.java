package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public NewsArrayAdapter(Context context, String[] values) {
        super(context, R.layout.news_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.news_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        String s = values[position];

//        if (s.equals("enot")) {
//            imageView.setImageResource(R.drawable.enot);
//        } else if (s.equals("pikachu")) {
//            imageView.setImageResource(R.drawable.pikachu);
//        } else if (s.equals("bulbasaur")) {
//            imageView.setImageResource(R.drawable.bulbasaur);
//        } else {
//            imageView.setImageResource(R.drawable.rain);
//        }

        return rowView;
    }

}
