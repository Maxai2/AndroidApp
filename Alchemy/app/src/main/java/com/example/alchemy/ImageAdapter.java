package com.example.alchemy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int itemBackground;
    List<Integer> imageIDs;

    public ImageAdapter(Context c, List<Integer> imageIDs) {
        context = c;
        this.imageIDs = imageIDs;
    }

    public int getCount() {
        return imageIDs.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View newView = inflater.inflate(R.layout.item_gallery, null);
        ImageView imageView = newView.findViewById(R.id.imagebutton);
        imageView.setImageResource(imageIDs.get(position));

        return newView;
    }
}