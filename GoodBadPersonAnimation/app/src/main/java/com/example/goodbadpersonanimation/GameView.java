package com.example.goodbadpersonanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {
    private Bitmap bmpBlood;
//    private SurfaceHolder holder;
    private Context context;
    private GameLoopThread gameLoopThread;
    private long lastClick;
//    private Sprite sprite;
//    private int x = 0;
//    private int xSpeed = 1;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<TempSprite> temps = new ArrayList<TempSprite>();

    public GameView(Context context) {
        super(context);
        this.context = context;
        this.gameLoopThread = new GameLoopThread(this);
//        holder = getHolder();
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        bmpBlood = drawableToBitmap(this.context.getDrawable(R.drawable.blood));
    }

    private void createSprites() {
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.bad6));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));
        sprites.add(createSprite(R.drawable.good5));
        sprites.add(createSprite(R.drawable.good6));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (x == getWidth() - bmp.getWidth()) {
//            xSpeed = -1;
//        }
//        if (x == 0) {
//            xSpeed = 1;
//        }
//        x = x + xSpeed;
        canvas.drawColor(Color.BLACK);
//        if (x < getWidth() - bmp.getWidth()) {
//            x++;
//        }
//        canvas.drawBitmap(bmp, x, 10, null);
        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).onDraw(canvas);
        }

        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = drawableToBitmap(context.getDrawable(resouce));
        return new Sprite(this,bmp);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            float x = event.getX();
            float y = event.getY();
            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.isCollition(x, y)) {
                        sprites.remove(sprite);
                        temps.add(new TempSprite(temps, this, x, y, bmpBlood));
                        break;
                    }
                }
            }
        }
        return true;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


}


//public class GameView extends View {
//    private Bitmap bmp;
//    private Context context;
//
//    public GameView(Context context) {
//        super(context);
//        this.context = context;
//
//        bmp = drawableToBitmap(context.getDrawable(R.drawable.icon));
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.BLACK);
//        try {
//            canvas.drawBitmap(bmp, 10, 10, null);
//        } catch (Exception e) {
//            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public static Bitmap drawableToBitmap (Drawable drawable) {
//
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable)drawable).getBitmap();
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//
//        return bitmap;
//    }
//}
