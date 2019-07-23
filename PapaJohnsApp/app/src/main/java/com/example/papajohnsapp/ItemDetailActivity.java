package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.papajohnsapp.Model.CategoryCategItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {

    Spinner itemSize;
    TextView itemCount;
    TextView itemPrice;

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
        String categ = "";
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

        final CategoryCategItem categoryCategItem = new Gson().fromJson(item, CategoryCategItem.class);

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

            sizeArr.add("Маленькая, 23см - " + String.valueOf(categoryCategItem.CCIPrice[0]) + "\u20BC");
            sizeArr.add("Средняя, 30см - " + String.valueOf(categoryCategItem.CCIPrice[1]) + "\u20BC");
            sizeArr.add("Большая, 35см - " + String.valueOf(categoryCategItem.CCIPrice[2]) + "\u20BC");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sizeArr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            itemSize.setAdapter(adapter);
            itemSize.setSelection(1);

            itemSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[position]) + "\u20BC");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        itemCount = findViewById(R.id.itemCount);

        itemPrice = findViewById(R.id.itemPrice);

        if (categ.equals("Pasta")) {
            itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[0]) + "\u20BC");
        } else {
            itemPrice.setText(String.valueOf(categoryCategItem.CCIPrice[1]) + "\u20BC");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void decIncCount(View view) {
        switch (view.getId()) {
            case R.id.decCount:
                break;
            case R.id.incCount:
                break;
        }
    }

    public void addToBasket(View view) {
    }
}
