package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewsArrayAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<newItem> posts = new ArrayList<>();

    public NewsArrayAdapter(Context context, ArrayList<newItem> posts) {
//        super(context, R.layout.news_item);
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.news_item, parent, false);

        TextView label = (TextView) rowView.findViewById(R.id.label);
        TextView author = (TextView) rowView.findViewById(R.id.author);
        TextView timeStamp = (TextView) rowView.findViewById(R.id.timeStamp);
        ImageView logo = (ImageView) rowView.findViewById(R.id.logo);

        label.setText(posts.get(position).text);
        author.setText(posts.get(position).author);
        timeStamp.setText(posts.get(position).timeStamp);
        logo.setImageResource(posts.get(position).img);

        return rowView;
    }

}