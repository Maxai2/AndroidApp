package com.example.magicshake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ShakeListener mShaker;
    TextView magicText;
    EditText takenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magicText = findViewById(R.id.magicText);
        takenText = findViewById(R.id.takenText);

        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                vibe.vibrate(100);

//                new AlertDialog.Builder(getApplicationContext())
//                        .setPositiveButton(android.R.string.ok, null)
//                        .setMessage("Shooken!")
//                        .show();

                if (!takenText.getText().equals("")) {
                    magicText.setText(takenText.getText());
                    takenText.setText("");
                    takenText.clearFocus();

                    hideKeyboard();
                }
            }
        });
    }

    public void hideKeyboard() {

        View view = this.getCurrentFocus();

        if (view == null) {
            view = new View(this);
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        //Find the currently focused view, so we can grab the correct window token from it.
//        View view = this.getCurrentFocus();
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(this);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onResume()
    {
        mShaker.resume();
        super.onResume();
    }
    @Override
    public void onPause()
    {
        mShaker.pause();
        super.onPause();
    }
}