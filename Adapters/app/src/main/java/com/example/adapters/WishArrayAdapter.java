package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

interface MyInterface{
    void onChangeCost(double b);
}

public class WishArrayAdapter extends BaseAdapter {

    MyInterface onchangecost;

    private final Context context;
    private ArrayList<WishItem> wishes = new ArrayList<>();
    private String sign;

    public WishArrayAdapter(Context context, ArrayList<WishItem> wishes, String sign, MyInterface onchangecost) {
        this.context = context;
        this.wishes = wishes;
        this.sign = sign;
        this.onchangecost = onchangecost;
    }

    public boolean setOnChangeCost(MyInterface myInterface)
    {
        onchangecost = myInterface;
        return true;
    }

    public boolean changeSign(String sign) {
        this.sign = sign;
        return true;
    }

    @Override
    public int getCount() {
        return wishes.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.wish_item, parent, false);

        ImageView img = (ImageView) rowView.findViewById(R.id.img);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView price = (TextView) rowView.findViewById(R.id.price);
        CheckBox isWish = (CheckBox) rowView.findViewById(R.id.isWish);

        isWish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wishes.get(position).isWish = isChecked;

                double amount = 0;
                for (WishItem item : wishes) {
                    if (item.isWish) {
                        amount += item.price;
                    }
                }

                onchangecost.onChangeCost(amount);
            }
        });

        img.setImageResource(wishes.get(position).img);
        title.setText(wishes.get(position).title);
        price.setText(String.format("%.2f", wishes.get(position).price) + " " + sign);
        isWish.setChecked(wishes.get(position).isWish);

        return rowView;
    }
}
