package com.example.papajohnsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.papajohnsapp.Model.StoreItem;
import com.example.papajohnsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoreBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<StoreItem> storeItems = new ArrayList<>();

    public StoreBaseAdapter(Context context, ArrayList<StoreItem> storeItems) {
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

        View rowView = inflater.inflate(R.layout.store_item, parent, false);

        ImageView storePic = rowView.findViewById(R.id.storePic);
        TextView storeName = rowView.findViewById(R.id.storeName);
        TextView storeStreet = rowView.findViewById(R.id.storeStreet);
        TextView storeWorkTime = rowView.findViewById(R.id.storeWorkTime);

        storePic.setImageResource(storeItems.get(position).storePicId);
        storeName.setText(storeItems.get(position).storeName);
        storeStreet.setText(storeItems.get(position).storeStreet);
        storeWorkTime.setText(storeItems.get(position).storeWorkTime);

        return rowView;
    }
}
