package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.papajohnsapp.Adapters.BasketBaseAdapter;
import com.example.papajohnsapp.Interfaces.AmountInterface;
import com.example.papajohnsapp.Model.BasketItem;

import static com.example.papajohnsapp.MainActivity.basketItems;

public class BasketActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        context = this;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Basket");

        ListView basketList = findViewById(R.id.basketList);

        ((TextView)findViewById(R.id.basketAmount)).setText(String.valueOf(amountCh()));

        final BasketBaseAdapter adapter = new BasketBaseAdapter(context, new AmountInterface() {
            @Override
            public void onAmontCost(int c) {
                ((TextView)findViewById(R.id.basketAmount)).setText(String.valueOf(c));
            }
        });

        basketList.setAdapter(adapter);

        Button order = findViewById(R.id.order);

        if (basketItems.size() == 0) {
            order.setEnabled(false);
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basketItems.size() != 0) {
                    new AlertDialog.Builder(context)
                            .setTitle("Info!")
                            .setMessage("Order all items?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    basketItems.clear();
                                    ((TextView)findViewById(R.id.basketAmount)).setText(String.valueOf(amountCh()));
                                    adapter.notifyDataSetChanged();
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
            }
        });
    }

    private int amountCh() {
        int amount = 0;

        for (BasketItem item: basketItems) {
            amount += Integer.parseInt(item.basketItemPrice);
        }

        return amount;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        menuItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(100, returnIntent);
                finish();
                return true;
            case R.id.store_action:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
