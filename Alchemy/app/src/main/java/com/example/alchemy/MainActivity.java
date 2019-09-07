package com.example.alchemy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    List<Integer> imageIDs;
    ArrayList<ElementForAlchemistry> elements = new ArrayList<>();
    Map<Map<String, String>, Integer> alchemyPair = new HashMap<>();
    Gallery gallery;
    ImageAdapter imageAdapter;

    private ViewGroup rootLayout;
    private int xPos;
    private int yPos;
    private int commonSize = 150;

    long startTime = 0;
//    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        data = findViewById(R.id.data);

        rootLayout = findViewById(R.id.root);

        alchemyPair.put(new HashMap<String, String>(){{put("fire", "water");}}, R.drawable.alcohol);
        alchemyPair.put(new HashMap<String, String>(){{put("fire", "wind");}}, R.drawable.energy);
        alchemyPair.put(new HashMap<String, String>(){{put("earth", "water");}}, R.drawable.swamp);
        alchemyPair.put(new HashMap<String, String>(){{put("energy", "swamp");}}, R.drawable.life);
        alchemyPair.put(new HashMap<String, String>(){{put("water", "life");}}, R.drawable.seaweed);

        imageIDs = new ArrayList<>();

        imageIDs.add(R.drawable.fire);
        imageIDs.add(R.drawable.earth);
        imageIDs.add(R.drawable.wind);
        imageIDs.add(R.drawable.water);

        gallery = (Gallery) findViewById(R.id.gallery);

        imageAdapter = new ImageAdapter(this, imageIDs);

        gallery.setAdapter(imageAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(getBaseContext(), getResources().getResourceEntryName(imageIDs.get(position)) + "",
//                        Toast.LENGTH_SHORT).show();

//                ImageView imageView = (ImageView) findViewById(R.id.image1);
//                imageView.setTag(imageIDs.get(position));
//                imageView.setImageResource(imageIDs.get(position));

                if (elements.size() == 63) {
                    return;
                }

                ImageView iv = new ImageView(parent.getContext());

                iv.setTag(imageIDs.get(position));
                iv.setImageResource(imageIDs.get(position));

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(commonSize, commonSize);

                layoutParams.leftMargin = 5;
                layoutParams.topMargin = 9 + (elements.size() / 7) * commonSize;

//                while(true) {
                    for (ElementForAlchemistry element : elements) {
                        if(element.elemXPos == layoutParams.leftMargin && element.elemYPos == layoutParams.topMargin) {
//                            if (elements.size() % 7 == 0) {
//                                layoutParams.topMargin += commonSize;
//                            } else {
                                layoutParams.leftMargin = element.elemXPos + commonSize;
//                                layoutParams.topMargin += (elements.size() / 7) * commonSize;
//                            }
                        }
                    }
//                }

                iv.setLayoutParams(layoutParams);

                elements.add(new ElementForAlchemistry(layoutParams.leftMargin, layoutParams.topMargin, getResources().getResourceEntryName(imageIDs.get(position)), imageIDs.get(position)));
                rootLayout.addView(iv, layoutParams);

                iv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(final View view, MotionEvent event) {
                        int X = (int) event.getRawX();
                        int Y = (int) event.getRawY();

                        int pointerCount = event.getPointerCount();

                        final ImageView iv = (ImageView) view;
                        int curIndex = 0;

                        ElementForAlchemistry ea = new ElementForAlchemistry();
                        for (ElementForAlchemistry element : elements) {
                            if (element.elemTag == (int)iv.getTag()) {
                                ea = elements.get(curIndex);
                                break;
                            }
                            curIndex++;
                        }

                        switch (event.getAction() & MotionEvent.ACTION_MASK) {

                            case MotionEvent.ACTION_DOWN:
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                xPos = X - layoutParams.leftMargin;
                                yPos = Y - layoutParams.topMargin;
                                break;

                            case MotionEvent.ACTION_UP:
                                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) view.getLayoutParams();

                                ea.elemXPos = par.leftMargin;
                                ea.elemYPos = par.topMargin;

                                for (final ElementForAlchemistry element : elements) {
                                    if (!element.elemName.equals(ea.elemName)) {
                                        if ((element.elemXPos - (commonSize / 2) <= ea.elemXPos && ea.elemXPos <= element.elemXPos + (commonSize / 2)) &&
                                                (element.elemYPos - (commonSize / 2) <= ea.elemYPos && ea.elemYPos <= element.elemYPos + (commonSize / 2))) {

                                            final ElementForAlchemistry finalEa1 = ea;
                                            int resultElem = alchemyPair.get(new HashMap<String, String>(){{put(element.elemName, finalEa1.elemName);}}) == null ? 0 : alchemyPair.get(new HashMap<String, String>(){{put(element.elemName, finalEa1.elemName);}});

                                            if (resultElem == 0) {
                                                resultElem = alchemyPair.get(new HashMap<String, String>(){{put(finalEa1.elemName, element.elemName);}}) == null ? 0 : alchemyPair.get(new HashMap<String, String>(){{put(finalEa1.elemName, element.elemName);}});;
                                            }

                                            if (resultElem != 0) {
//                                                Toast.makeText(getApplicationContext(), resultElem, Toast.LENGTH_SHORT).show();

                                                view.setTag(resultElem);
                                                ((ImageView) view).setImageResource(resultElem);

                                                ea.elemName = getResources().getResourceEntryName(resultElem);
                                                ea.elemTag = resultElem;

                                                imageIDs.add(resultElem);
                                                imageAdapter = new ImageAdapter(getApplicationContext(), imageIDs);
                                                gallery.setAdapter(imageAdapter);
                                            }

                                            break;
                                        }
                                    }
                                }

                                if (startTime == 0) {
                                    startTime = System.currentTimeMillis();
                                } else {
                                    if (System.currentTimeMillis() - startTime < 200) {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setMessage("Удалить картинку?");
                                        final ElementForAlchemistry finalEa = ea;
                                        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                view.setVisibility(View.GONE);

                                                elements.remove(finalEa);
                                            }
                                        });

                                        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                    startTime = System.currentTimeMillis();
                                }
                                break;

                            case MotionEvent.ACTION_MOVE:

                                if (pointerCount == 1) {
                                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

                                    params.leftMargin = X - xPos;
                                    params.topMargin = Y - yPos;

                                    if (5 <= params.leftMargin && params.leftMargin <= 930 && 9 <= params.topMargin && params.topMargin <= 1244) {
                                        view.setLayoutParams(params);
                                    }

                                }

//                                if (pointerCount == 2) { // rotate image
//                                    view.setRotation(view.getRotation() + 5.0f);
//                                }

                                break;
                        }

                        rootLayout.invalidate();
                        return true;
                    }
                });

            }
        });
    }

    private static void refreshGallery(String mCurrentPhotoPath, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    @Override
    public boolean onTouch(final View view, MotionEvent event) {
        return true;
    }

//    public void onClick(View view) {
//        ImageView v = (ImageView)view;
//        Integer id = (Integer)v.getTag();
//        imageIDs.add(id);
//        imageAdapter.notifyDataSetChanged();
//    }
}
