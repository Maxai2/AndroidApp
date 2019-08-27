package com.example.boatpainting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class BoatDrawer extends View {

    public BoatDrawer(Context context) {
        super(context);
    }

    private Paint p = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(44, 150, 200));
        canvas.drawPaint(p);

        p.setColor(Color.rgb(31, 0, 200));
        canvas.drawRect(0, 800, MainActivity.width, MainActivity.height, p);

        int centerX = 500;
        int centerY = 500;
        p.setAntiAlias(true);
        p.setColor(Color.rgb(254, 225, 0));
        canvas.drawCircle(centerX, centerY, 300, p);

        p.setColor(Color.rgb(128, 0, 0));

        Path pathBoat = new Path();
        pathBoat.moveTo(760, 600);
        pathBoat.lineTo(980, 600);
        pathBoat.lineTo(980, 680);
        pathBoat.lineTo(1250, 680);
        pathBoat.lineTo(1250, 640);
        pathBoat.lineTo(1450, 640);
        pathBoat.lineTo(1350, 800);
        pathBoat.lineTo(880, 800);
        pathBoat.lineTo(760, 600);
        pathBoat.close();

        canvas.drawPath(pathBoat, p);

        p.setColor(Color.rgb(255, 255, 255));

        Path pathSail = new Path();
        pathSail.moveTo(1050, 180);
        pathSail.lineTo(1200, 450);
        pathSail.lineTo(1050, 680);
        pathSail.lineTo(1050, 180);
        pathSail.close();

        canvas.drawPath(pathSail, p);

        // заливка градиентом
//        Shader shader = new LinearGradient(0, 0, 0, MainActivity.height, Color.BLUE, Color.CYAN, Shader.TileMode.CLAMP);
//        p.setShader(shader);
//        canvas.drawRect(new RectF(0, 0, MainActivity.width, MainActivity.height), p);
//        p.setShader(null);


        // звёздочки
        /*
        p.setStrokeCap(Paint.Cap.ROUND);
        for (int i = 0; i < 200; i++) {
            p.setARGB((int) (Math.random() * 128), 255, 255, 255);
            p.setStrokeWidth((int) (Math.random() * 15));
            canvas.drawPoint((int) (Math.random() * MainActivity.width), (int) (Math.random() * MainActivity.height), p);
        }
        */

        // солнышко
        /*
        int centerX = 250;
        int centerY = 100;
        p.setAntiAlias(true);
        p.setColor(Color.YELLOW);
        canvas.drawCircle(centerX, centerY, 40, p);
        */

        // лучики
        /*
        p.setARGB(64, 255, 255, 255);
        p.setStrokeWidth(1);
        float degree = 0.125f;
        for (float i = 0; i < 360; i += degree) {
            canvas.drawLine(centerX, centerY, -1500, centerY, p);
            canvas.rotate(degree, centerX, centerY);
        }
        */

        // трава
        /*
        p.setColor(Color.parseColor("#A4C639"));
        canvas.drawRect(0, 550, MainActivity.width, MainActivity.height, p);
        p.setStrokeCap(Paint.Cap.ROUND);
        */

        // одуванчики
        /*
        for (int i = 0; i < 1000; i++) {
            float y = (int) (Math.random() * MainActivity.height) + 560;
            p.setARGB(32, 255, 255, 0);
            p.setStrokeWidth((int) ((y / 130) * (y / 130)));
            canvas.drawPoint((int) (Math.random() * MainActivity.width), y, p);
        }
        */

        // текст
        /*
        p.setColor(Color.BLUE);
        p.setTextSize(40);
        canvas.rotate(-20, 30, 675);
        canvas.drawText("Android Q", 30, 675, p);
        */

        // бульбазавр
        /*
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.bulbasaur);
        canvas.drawBitmap(Bitmap.createScaledBitmap(b, 200, 200, false), 650, 450, p);
        */
    }
}
