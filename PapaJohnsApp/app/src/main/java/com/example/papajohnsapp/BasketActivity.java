package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.papajohnsapp.Adapters.BasketBaseAdapter;
import com.example.papajohnsapp.Model.BasketItem;

import static com.example.papajohnsapp.MainActivity.basketItems;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Basket");

        ListView basketList = findViewById(R.id.basketList);

        ((TextView)findViewById(R.id.basketAmount)).setText(String.valueOf(amountCh()));

        BasketBaseAdapter adapter = new BasketBaseAdapter(this, new AmountInterface() {
            @Override
            public void onAmontCost(int c) {
                ((TextView)findViewById(R.id.basketAmount)).setText(String.valueOf(c));
            }
        });

        basketList.setAdapter(adapter);
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
