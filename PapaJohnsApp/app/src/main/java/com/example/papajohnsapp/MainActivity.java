package com.example.papajohnsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.papajohnsapp.Adapters.CategoryBaseAdapter;
import com.example.papajohnsapp.Interfaces.onItemClickListner;
import com.example.papajohnsapp.Model.BasketItem;
import com.example.papajohnsapp.Model.CategoryItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView basketItem;
    public static ArrayList<BasketItem> basketItems = new ArrayList<>();

    int[] categImg = new int[] {
            R.drawable.pizza, R.drawable.pasta
    };

    String[] categName = new String[] {
            "Pizza", "Pasta"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView categList = findViewById(R.id.categList);

        ArrayList<CategoryItem> categArr = new ArrayList<>();

        for (int i = 0; i < categName.length; ++i) {
            categArr.add(new CategoryItem(categImg[i], categName[i]));
        }

        CategoryBaseAdapter adapter = new CategoryBaseAdapter(this, categArr);

        categList.setAdapter(adapter);

        adapter.setOnItemClickListner(new onItemClickListner() {
            @Override
            public void onItemClick(Intent intent) {
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setupBadge();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent intentBas = new Intent(this, BasketActivity.class);
                startActivityForResult(intentBas, 100);
                return true;
            case R.id.store_action:
                Intent intentSto = new Intent(this, StoreActivity.class);
                startActivity(intentSto);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    int badgeCount = 10;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        if (badgeCount > 0) {
//            Drawable d  = getDrawable(R.drawable.basket);
//
//            ActionItemBadge.update(this, menu.findItem(R.id.basket),d, ActionItemBadge.BadgeStyles.BLUE,badgeCount);
//        } else {
//            ActionItemBadge.hide(menu.findItem(R.id.basket));
//        }

        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        menu.findItem(R.id.delete_action).setVisible(false);

        View actionView = menuItem.getActionView();
        basketItem = (TextView) actionView.findViewById(R.id.basket_badge);

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
}
