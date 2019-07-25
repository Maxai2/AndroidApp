package com.example.papajohnsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.papajohnsapp.Adapters.StoreBaseAdapter;
import com.example.papajohnsapp.Model.StoreItem;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    int[] storePics = new int[] {
            R.drawable.nizami_store,
            R.drawable.sahil_store,
            R.drawable.genclik_store,
            R.drawable.acami_store,
            R.drawable.park_bulvar_store,
            R.drawable.pizza_store_default
    };

    String[] storeNames = new String[] {
            "Низами",
            "Сахиль",
            "Гянджлик",
            "Аджеми",
            "Парк Бульвар",
            "Ахмедлы"
    };

    String[] storeStreets = new String[] {
            "ул. Мирзага Алиева 138",
            "ул. Низами 106 A",
            "пр. Ататюрка 29",
            "ул. Джавадхана 15ц",
            "пр-т Нефтянников 92",
            "ул. М.Хади 106"
    };

    String[] storeWorkTimes = new String[] {
            "11:00 - 23:00",
            "11:00 - 23:00",
            "11:00 - 23:00",
            "11:00 - 06:00",
            "10:00 - 23:00",
            "11:00 - 23:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Stores");

        ListView storeList = findViewById(R.id.storeList);

        ArrayList<StoreItem> storeItems = new ArrayList<>();

        for (int i = 0; i < storeNames.length; ++i) {
            storeItems.add(new StoreItem(storePics[i], storeNames[i], storeStreets[i], storeWorkTimes[i]));
        }

        StoreBaseAdapter adapter = new StoreBaseAdapter(this, storeItems);

        storeList.setAdapter(adapter);
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
}
