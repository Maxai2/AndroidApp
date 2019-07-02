package com.example.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvlsel);
    }

    public void selLvl(View view) {
        Intent intent = new Intent(this, FieldLvl.class);

        switch (view.getId()) {
            case R.id.easy:
                intent.putExtra("lvl", "easy");
                break;
            case R.id.middle:
                intent.putExtra("lvl", "middle");
                break;
            case R.id.hard:
                intent.putExtra("lvl", "hard");
                break;
            case R.id.skull:
                intent.putExtra("lvl", "skull");
                break;
        }

        startActivity(intent);
    }
}
