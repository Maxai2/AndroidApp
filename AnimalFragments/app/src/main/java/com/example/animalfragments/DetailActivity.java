package com.example.animalfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ANIMAL = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AnimalDetailFragment adf = (AnimalDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int animalId = (int) getIntent().getExtras().get(EXTRA_ANIMAL);
        adf.setAnimal(animalId);
    }
}
