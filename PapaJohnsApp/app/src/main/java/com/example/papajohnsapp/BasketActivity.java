package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.papajohnsapp.Adapters.BasketBaseAdapter;
import com.example.papajohnsapp.Adapters.CategoryBaseAdapter;
import com.example.papajohnsapp.Model.BasketItem;
import com.example.papajohnsapp.Model.CategoryItem;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.papajohnsapp.MainActivity.basketItems;

public class BasketActivity extends AppCompatActivity {

    TextView basketItem;

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

        BasketBaseAdapter adapter = new BasketBaseAdapter(this, basketItems);

        basketList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        basketItem = actionView.findViewById(R.id.basket_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    private void setupBadge() {

        if (basketItem != null) {
            if (basketItems.size() == 0) {
                if (basketItem.getVisibility() != View.GONE) {
                    basketItem.setVisibility(View.GONE);
                }
            } else {
                basketItem.setText(String.valueOf(Math.min(basketItems.size(), 99)));
                if (basketItem.getVisibility() != View.VISIBLE) {
                    basketItem.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_cart:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            case R.id.store_action:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
