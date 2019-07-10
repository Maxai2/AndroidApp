package com.example.adapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity {

    static final int[] imgItems = new int[] {
        R.drawable.drill, R.drawable.screewdriver, R.drawable.vertical_cutter, R.drawable.drill, R.drawable.screewdriver, R.drawable.vertical_cutter, R.drawable.drill, R.drawable.screewdriver, R.drawable.vertical_cutter
    };

    static final String[] titleItems = new String[] {
      "Drill", "Screewdriver kit", "Trimmer Mini Wood", "Drill", "Screewdriver kit", "Trimmer Mini Wood", "Drill", "Screewdriver kit", "Trimmer Mini Wood"
    };

    static final double[] priceItems = new double[] {
            61.27, 198, 50.14, 61.27, 198, 50.14, 61.27, 198, 50.14
    };

    ArrayList<WishItem> wishes;
    WishArrayAdapter wishArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);

        RadioGroup rg = (RadioGroup) findViewById(R.id.currency);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String s="";

                double convert=0;
                switch (checkedId) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        s = "UAH";
                        convert = 27.02;
                        break;
                }

                for(WishItem item : wishes)
                {
                    item.price*=convert;
                }
                wishArrayAdapter.changeSign(s);
                wishArrayAdapter.notifyDataSetChanged();
            }
        });

        GridView wishList = findViewById(R.id.wishList);

        wishes = new ArrayList<>();

        for (int i = 0; i < titleItems.length; ++i) {
            WishItem w = new WishItem(imgItems[i], titleItems[i], priceItems[i]);

            wishes.add(w);
        }

        wishArrayAdapter = new WishArrayAdapter(this, wishes,"USD");
        wishList.setAdapter(wishArrayAdapter);
    }
}
