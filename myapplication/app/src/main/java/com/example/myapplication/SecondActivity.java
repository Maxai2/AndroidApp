package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    String TAG = "SECOND_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void SendWho(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Letter subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Some text");
        this.startActivity(intent);
    }

    public void Speach(View view) {
        Intent intent = new Intent(this, Speech.class);
        this.startActivity(intent);
    }
}
