package com.example.papajohnsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView basketCount;
    int basketCountNum;

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.basket:
                return true;
            case R.id.store_action:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    int badgeCount = 10;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

//        if (badgeCount > 0) {
//            Drawable d  = getDrawable(R.drawable.basket);
//
//            ActionItemBadge.update(this, menu.findItem(R.id.basket),d, ActionItemBadge.BadgeStyles.BLUE,badgeCount);
//        } else {
//            ActionItemBadge.hide(menu.findItem(R.id.basket));
//        }

        new ActionItemBadgeAdder().act(this).menu(menu).title(R.string.sample_2).itemDetails(0, SAMPLE2_ID, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).add(bigStyle, 1);
        return super.onCreateOptionsMenu(menu);
        final MenuItem menuItem = menu.findItem(R.id.basket);

        View actionView = MenuItemCompat.getActionView(menuItem);
        basketCount = (TextView) actionView.findViewById(R.id.cart_badge);

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

        if (basketCount != null) {
            if (basketCountNum == 0) {
                if (basketCount.getVisibility() != View.GONE) {
                    basketCount.setVisibility(View.GONE);
                }
            } else {
                basketCount.setText(String.valueOf(Math.min(basketCountNum, 99)));
                if (basketCount.getVisibility() != View.VISIBLE) {
                    basketCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
