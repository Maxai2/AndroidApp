package com.example.truereopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        sp = getPreferences(MODE_PRIVATE);

        Configuration conf = getResources().getConfiguration();
        if (conf.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (sp.getString("count", "").equals("")) {
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("count", "1");
                ed.apply();

                text.setText("1");
            } else {
                int c = Integer.parseInt(sp.getString("count", "0"));
                c++;

                SharedPreferences.Editor ed = sp.edit();
                ed.putString("count", String.valueOf(c));
                ed.apply();

                text.setText(String.valueOf(c));
            }
        } else {
            text.setText(sp.getString("count", ""));
        }

    }

    public void SecondActGo(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
