package com.example.buttonplay;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    ArrayList<String> fontsArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontsArr.add("alfa_slab_one");
        fontsArr.add("arizonia");
        fontsArr.add("bonbon");
        fontsArr.add("butcherman");
        fontsArr.add("sacramento");
    }

    public void onClick(View view) {
        Button btn = (Button)view;
//        btn.setWidth(btn.getWidth() + 25);
        btn.setWidth(this.random.nextInt((500 - 100) + 1) + 100);
        btn.setTextSize(this.random.nextInt((36 - 8) + 1) + 8);
        Typeface font = Typeface.create(fontsArr.get(this.random.nextInt((4 - 0) + 1) + 0), Typeface.NORMAL);
        btn.setTypeface(font);
        //(fontsArr.get(((5 - 0) + 1) + 5)).toString());Typeface.createFromAsset(getAssets(), R.font.arizonia);
    }
}
