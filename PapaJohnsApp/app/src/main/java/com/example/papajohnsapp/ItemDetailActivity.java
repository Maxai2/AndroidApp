package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.papajohnsapp.Model.BasketItem;
import com.example.papajohnsapp.Model.CategoryCategItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.papajohnsapp.MainActivity.basketItems;

public class ItemDetailActivity extends AppCompatActivity {

    TextView basketItem;
    Spinner itemSize;
    TextView itemCount;
    TextView itemPrice;
    CategoryCategItem categoryCategItem;
    int count = 1;
    String categ;

    String[] sizes = new String[] {
            "Маленькая, 23см",
            "Средняя, 30см",
            "Большая, 35см"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detail");
        }

        String item = "";
        categ = "";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if(extras == null) {
                item = null;
                categ = null;
            } else {
                item =  extras.getString("itemName");
                categ = extras.getString("itemCateg");
            }
        } else {
            item = (String)savedInstanceState.getSerializable("itemName");
            categ = (String)savedInstanceState.getSerializable("itemCateg");
        }

        categoryCategItem = new Gson().fromJson(item, CategoryCategItem.class);

        ImageView itemPic = findViewById(R.id.itemPic);
        itemPic.setImageResource(categoryCategItem.CCIPic);

        TextView itemName = findViewById(R.id.itemName);
        itemName.setText(categoryCategItem.CCIName);

        TextView itemIng = findViewById(R.id.itemIng);
        itemIng.setText(categoryCategItem.CCIIngridients);

        itemSize = findViewById(R.id.itemSize);

        if (categ.equals("Pasta")) {
            itemSize.setVisibility(View.GONE);
        } else {
            ArrayList<String> sizeArr =  new ArrayList<>();

            sizeArr.add(sizes[0] + " - " + categoryCategItem.CCIPrice[0] + "\u20BC");
            sizeArr.add(sizes[1] + " - " + categoryCategItem.CCIPrice[1] + "\u20BC");
            sizeArr.add(sizes[2] + " - " + categoryCategItem.CCIPrice[2] + "\u20BC");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeArr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            itemSize.setAdapter(adapter);
            itemSize.setSelection(1);

            itemSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[position] * count));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        itemCount = findViewById(R.id.itemCount);

        itemPrice = findViewById(R.id.itemPrice);

        if (categ.equals("Pasta")) {
            itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[0]));
        } else {
            itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[1]));
        }
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

    public void decIncCount(View view) {
        count = Integer.parseInt(itemCount.getText().toString());

        switch (view.getId()) {
            case R.id.decCount:
                count--;
                if (count == 1) {
                    view.setEnabled(false);
                }
                break;
            case R.id.incCount:
                count++;
                if (count == 2) {
                    findViewById(R.id.decCount).setEnabled(true);
                }
                break;
        }

        itemCount.setText(String.valueOf(count));

        itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[(int)itemSize.getSelectedItemId()] * count));
    }

    public void addToBasket(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Info!")
                .setMessage("Add to basket?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (categ.equals("Pizza")) {
                            basketItems.add(new BasketItem(categoryCategItem.CCIPic, categoryCategItem.CCIName, sizes[(int)itemSize.getSelectedItemId()], itemCount.getText().toString()));
                        } else {
                            basketItems.add(new BasketItem(categoryCategItem.CCIPic, categoryCategItem.CCIName, itemCount.getText().toString()));
                        }

                        setupBadge();
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
