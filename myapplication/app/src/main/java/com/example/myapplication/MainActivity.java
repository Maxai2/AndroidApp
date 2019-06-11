package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

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

    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Parameters params;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA/*,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECORD_AUDIO */
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();


        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            getSupportActionBar().setTitle("NO CAMERA!");
        } else {
            getCamera();
        }

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


    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
            } catch (RuntimeException e) {
                getSupportActionBar().setTitle("getCamera ERROR");
            }
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || !hasFlash) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
        }
    }


    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
        }
    }

    @Override
    public void onClick(View v) {
        MotionEvent event;
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
            case R.id.btnF:
                if (!isFlashOn) {
                    turnOnFlash();
                    isFlashOn = true;
                } else {
                    turnOffFlash();
                    isFlashOn = false;
                }
                break;
            case R.id.btnSecAct:
                Intent intent = new Intent(this, SecondActivity.class);
                this.startActivity(intent);
                break;
        }
    }

//    public void BtnClick(View view) {
//        Toast.makeText(this, "make toast!", Toast.LENGTH_LONG).show();
//    }
}
