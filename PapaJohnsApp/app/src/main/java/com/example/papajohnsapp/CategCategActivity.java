package com.example.papajohnsapp;

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

import static com.example.papajohnsapp.MainActivity.setupBadge;

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
            "Курица Гриль, Свежие Грибы, Соус Барбекю, Сыр Моцарелла",
            "Пепперони, Итальянские Cосиски, Ветчина, Свежие Грибы, Зелёный Перец, Чёрные Оливки, Сыр Моцарелла",
            "Пепперони, Итальянские Cосиски, Говядина, Ветчина, Сыр Моцарелла",
            "Курица Гриль, Ананасы, Экстра Моцарелла",
            "Говядина, Свежие Грибы, Соус Барбекю, Сыр Моцарелла"
    };

//    int[][] categCategPricesPizza = new int[][] {
//            {8, 13, 18},
//            {10, 16, 20},
//            {11, 18, 23},
//            {9, 14, 19},
//            {8, 13, 18}
//    };
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
            "Спагетти, Пепперони, Ветчина, Итальянские Сосиски, Зелёный Перец, Свежие Грибы, Пицца-Соус, Сыр Моцарелла",
            "Спагетти, Курица Гриль, Свежие Грибы, Соус Рэнч, Сыр Моцарелла",
            "Спагетти, Пармезан, Орегано, Пицца-Соус, Сыр Моцарелла"
    };

//    int[][] categCategPricesPasta = new int[][] {
//            {7},
//            {7},
//            {6}
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ_categ);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                            categCategIngsPizza[i]));
                }
                break;
            case "Pasta":
                for (int i = 0; i < categCategNamesPasta.length; ++i) {
                    categoryCategItems.add(new CategoryCategItem(
                            categCategPicsPasta[i],
                            categCategNamesPasta[i],
                            categCategIngsPasta[i]));
                }
                break;
                default:
                    return;
        }

        ItemFromCategBaseAdapter itemFromCategBaseAdapter = new ItemFromCategBaseAdapter(this, categoryCategItems);
        categCategList.setAdapter(itemFromCategBaseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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
