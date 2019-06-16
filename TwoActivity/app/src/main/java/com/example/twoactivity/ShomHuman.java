package com.example.twoactivity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShomHuman extends AppCompatActivity {

    String human;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            Human h = (Human)arguments.getSerializable(Human.class.getSimpleName());
            human = h.toString();
        } else {

        }

        setContentView(R.layout.activity_shom_human);
        tv = (TextView)findViewById(R.id.textV);
    }

    boolean showAc = true;
    boolean showTv = true;

    public void showAc(View view) {
        Button bt = (Button)view;

        switch (bt.getId()) {
            case R.id.ab:
                if (showAc) {
                    getSupportActionBar().setTitle(human);
                } else {
                    getSupportActionBar().setTitle("");
                }
                showAc = !showAc;
                break;
            case R.id.toa:
                Toast t = Toast.makeText(this, human, Toast.LENGTH_SHORT);
//                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                break;
            case R.id.di:
                new AlertDialog.Builder(this)
                    .setTitle("Info!")
                    .setMessage(human)
                    .setNeutralButton("Ok", null)
                    .show();
                break;
            case R.id.tv:
                if(showTv) {
                    tv.setText(human);
                } else {
                    tv.setText("");
                }
                showTv = !showTv;
                break;
            case R.id.back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
