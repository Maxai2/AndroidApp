package com.example.papajohnsapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.papajohnsapp.Interfaces.AmountInterface;
import com.example.papajohnsapp.Model.BasketItem;
import com.example.papajohnsapp.R;

import static com.example.papajohnsapp.MainActivity.basketItems;

public class BasketBaseAdapter extends BaseAdapter {

    AmountInterface amountInterface;

    Context context;
    TextView itemCount;
    TextView basketItemPrice;

    public BasketBaseAdapter(Context context, AmountInterface amountInterface) {
        this.context = context;
        this.amountInterface = amountInterface;
    }

    @Override
    public int getCount() {
        return basketItems.size();
    }

    @Override
    public Object getItem(int position) {
        return basketItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.basket_item, parent, false);

        ImageView basketPic = rowView.findViewById(R.id.basketPic);
        TextView basketItemTitle = rowView.findViewById(R.id.basketItemTitle);
        TextView basketItemSize = rowView.findViewById(R.id.basketItemSize);
        itemCount = rowView.findViewById(R.id.itemCount);
        basketItemPrice = rowView.findViewById(R.id.basketItemPrice);

        basketPic.setImageResource(basketItems.get(position).basItemPicId);
        basketItemTitle.setText(basketItems.get(position).basItemTitle);

        String sizeTemp = basketItems.get(position).basItemSize;
        if (sizeTemp == null) {
            basketItemSize.setVisibility(View.GONE);
        } else {
            basketItemSize.setText(sizeTemp);
        }

        itemCount.setText(basketItems.get(position).basItemCount);
        basketItemPrice.setText(basketItems.get(position).basketItemPrice);

        Button basketItemDelete = rowView.findViewById(R.id.basketItemDelete);

        basketItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Info")
                        .setMessage("Delete item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                basketItems.remove(position);
                                notifyDataSetChanged();

                                amountInterface.onAmontCost(amountCh());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        final Button decCount = rowView.findViewById(R.id.decCount);

        decCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(basketItems.get(position).basItemCount);

                count--;

                if (count == 1) {
                    v.setEnabled(false);
                }

                incDecPrice(count, position);
                amountInterface.onAmontCost(amountCh());
            }
        });

        if (!basketItems.get(position).basItemCount.equals("1")) {
            decCount.setEnabled(true);
        }

        Button incCount = rowView.findViewById(R.id.incCount);

        incCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(basketItems.get(position).basItemCount);

                count++;

                if (count == 2) {
                    decCount.setEnabled(true);
                }

                incDecPrice(count, position);
                amountInterface.onAmontCost(amountCh());
            }
        });

        return rowView;
    }

    private void incDecPrice(int count, int position) {

        basketItems.get(position).basItemCount = String.valueOf(count);

        itemCount.setText(basketItems.get(position).basItemCount);

        basketItems.get(position).basketItemPrice = String.valueOf(count * Integer.parseInt(basketItems.get(position).basketItemPriceFix));

        basketItemPrice.setText(basketItems.get(position).basketItemPrice);
        notifyDataSetChanged();
    }

    private int amountCh() {
        int amount = 0;

        for (BasketItem item: basketItems) {
            amount += Integer.parseInt(item.basketItemPrice);
        }

        return amount;
    }
}
