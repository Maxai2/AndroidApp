package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.papajohnsapp.Adapters.ItemFromCategBaseAdapter;
import com.example.papajohnsapp.Model.CategoryCategItem;

import java.util.ArrayList;

import static com.example.papajohnsapp.MainActivity.basketCountNum;

public class CategCategActivity extends AppCompatActivity {

    int[] categCategPicsPizza = new int[] {
            R.drawable.chicken_barbecue,
            R.drawable.super_papa,
            R.drawable.meat,
            R.drawable.hawai,
            R.drawable.western_barbecue
    };

    String[] categCategNamesPizza = new String[] {
            "Чикен Барбекю",
            "Супер Папа",
            "Мясная",
            "Гавайская",
            "Вестерн Барбекю"
    };

    String[] categCategIngsPizza = new String[] {
            "\t\tКурица Гриль, Свежие Грибы, Соус Барбекю, Сыр Моцарелла",
            "\t\tПепперони, Итальянские Cосиски, Ветчина, Свежие Грибы, Зелёный Перец, Чёрные Оливки, Сыр Моцарелла",
            "\t\tПепперони, Итальянские Cосиски, Говядина, Ветчина, Сыр Моцарелла",
            "\t\tКурица Гриль, Ананасы, Экстра Моцарелла",
            "\t\tГовядина, Свежие Грибы, Соус Барбекю, Сыр Моцарелла"
    };

    int[][] categCategPricesPizza = new int[][] {
            {8, 13, 18},
            {10, 16, 20},
            {11, 18, 23},
            {9, 14, 19},
            {8, 13, 18}
    };

    //----------------------------------------------------------------------------

    int[] categCategPicsPasta = new int[] {
            R.drawable.pasta_super_pap,
            R.drawable.chicken_rench_pasta,
            R.drawable.mama_pasta
    };

    String[] categCategNamesPasta = new String[] {
            "Паста Супер Папа",
            "Чикен Рэнч Паста",
            "Мама Паста"
    };

    String[] categCategIngsPasta = new String[] {
            "\t\tСпагетти, Пепперони, Ветчина, Итальянские Сосиски, Зелёный Перец, Свежие Грибы, Пицца-Соус, Сыр Моцарелла",
            "\t\tСпагетти, Курица Гриль, Свежие Грибы, Соус Рэнч, Сыр Моцарелла",
            "\t\tСпагетти, Пармезан, Орегано, Пицца-Соус, Сыр Моцарелла"
    };

    int[][] categCategPricesPasta = new int[][] {
            {7},
            {7},
            {6}
    };

    TextView basketItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ_categ);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String categ = "";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if(extras == null) {
                categ = null;
            } else {
                categ=  extras.getString("categName");
            }
        } else {
            categ = (String)savedInstanceState.getSerializable("categName");
        }

        ListView categCategList = findViewById(R.id.categCategList);

        ArrayList<CategoryCategItem> categoryCategItems = new ArrayList<>();

        getSupportActionBar().setTitle(categ);
        switch (categ) {
            case "Pizza":
                for (int i = 0; i < categCategNamesPizza.length; ++i) {
                    categoryCategItems.add(new CategoryCategItem(
                            categCategPicsPizza[i],
                            categCategNamesPizza[i],
                            categCategIngsPizza[i],
                            categCategPricesPizza[i]));
                }
                break;
            case "Pasta":
                for (int i = 0; i < categCategNamesPasta.length; ++i) {
                    categoryCategItems.add(new CategoryCategItem(
                            categCategPicsPasta[i],
                            categCategNamesPasta[i],
                            categCategIngsPasta[i],
                            categCategPricesPasta[i]));
                }
                break;
                default:
                    return;
        }

        ItemFromCategBaseAdapter itemFromCategBaseAdapter = new ItemFromCategBaseAdapter(this, categoryCategItems, categ);
        categCategList.setAdapter(itemFromCategBaseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

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
            if (basketCountNum == 0) {
                if (basketItem.getVisibility() != View.GONE) {
                    basketItem.setVisibility(View.GONE);
                }
            } else {
                basketItem.setText(String.valueOf(Math.min(basketCountNum, 99)));
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
