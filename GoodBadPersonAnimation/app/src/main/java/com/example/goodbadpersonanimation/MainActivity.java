package com.example.goodbadpersonanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(getApplicationContext()));
    }
}