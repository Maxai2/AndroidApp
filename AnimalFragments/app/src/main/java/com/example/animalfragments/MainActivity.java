package com.example.animalfragments;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements AnimalListFragment.AnimalListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            AnimalDetailFragment details = new AnimalDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setAnimal(details.getAnimal());
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public void itemClicked(long id) {
        // если fragmentContainer существует, значит, это планшетная версия
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            AnimalDetailFragment details = new AnimalDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setAnimal((int)id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ANIMAL, (int) id);
            startActivity(intent);
        }
    }
}
