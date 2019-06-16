package com.example.twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText age;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        email = (EditText)findViewById(R.id.email);

        final Button bt = (Button)findViewById(R.id.sendBt);
//        bt.setEnabled(false);

//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.equals("")) {
//                    bt.setEnabled(false);
//                } else {
//                    bt.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    public void Send(View view) {
        String nameS = name.getText().toString();
        int ageS = Integer.parseInt(age.getText().toString().equals("") ? "0" : age.getText().toString());
        String emailS = email.getText().toString();

        if (!(nameS.equals("") || (ageS == 0) || emailS.equals(""))) {
            Human h = new Human(nameS, ageS, emailS);

            Intent intent = new Intent(this, ShomHuman.class);
            intent.putExtra(Human.class.getSimpleName(), h);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Fill empty field", Toast.LENGTH_SHORT).show();
        }
    }
}
