package com.example.papajohnsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.papajohnsapp.Model.StoreItem;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    int[] storePics = new int[] {
            R.drawable.nizamiStore,
            R.drawable.sahilStore,
            R.drawable.genclikStore,
            R.drawable.acamiStore,
            R.drawable.parkBulvarStore
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

        getSupportActionBar().setTitle("Stores");

        ListView storeList = findViewById(R.id.storeList);

        ArrayList<StoreItem> storeItems = new ArrayList<>();

        for (int i = 0; i < storeNames.length; ++i) {
            storeItems.add(new StoreItem(storePics[i], storeNames[i], storeStreets[i], storeWorkTimes[i]));
        }


    }
}
