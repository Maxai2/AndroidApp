package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//class MyClick implements View.OnClickListener {
//
//    @Override
//    public void onClick(View v) {
////        if (v.getId() == R.id.btn) {
////        }
//
//        Button b = (Button)v;
//        b.setText("Click me x2!!!");
//    }
//}


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);
//        btn.setOnClickListener(new MyClick());
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn.setOnClickListener((v)->{});
        btn.setOnClickListener(this); // call onClick in current class
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                    Toast t = Toast.makeText(this, "make toast1!", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 2, 3);
                    t.show();
                break;
            case R.id.btn2:
                    Toast.makeText(this, "make toast2!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn3:
//                    Toast.makeText(this, "make toast3!", Toast.LENGTH_LONG).show();
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "ピカチュウ!", Toast.LENGTH_LONG);

                LinearLayout toastContainer = (LinearLayout)toast.getView();
                TextView toastText = (TextView)toastContainer.getChildAt(0);
                toastText.setTextSize(35);

                ImageView img = new ImageView(getApplicationContext());
                img.setImageResource(R.drawable.pikachu);
                toastContainer.addView(img, 0);

                toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);

                toast.show();
            }
                break;
        }
    }

//    public void BtnClick(View view) {
//        Toast.makeText(this, "make toast!", Toast.LENGTH_LONG).show();
//    }
}
