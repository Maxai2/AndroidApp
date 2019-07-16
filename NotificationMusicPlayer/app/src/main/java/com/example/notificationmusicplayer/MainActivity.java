package com.example.notificationmusicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(View view) {

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;

        Context context = getApplicationContext();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "MusicPlayer";

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MusicPlayer",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("MusicPlayer channel description");

            channel.setSound(null, null);

            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        PendingIntent action = PendingIntent.getActivity(context,
                0, new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Prosto_Amiran")),
                PendingIntent.FLAG_CANCEL_CURRENT); // Flag indicating that if the described PendingIntent already exists, the current one should be canceled before generating a new one.

//        PendingIntent action = null;

        builder.setContentTitle("Notification Title")
                .setContentText("Notification Content")
                .setContentIntent(action)
                .setPriority(Notification.PRIORITY_HIGH) // приоритет для старых версий андроида
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.vinil))
                // http://stackoverflow.com/questions/35647821/android-notification-addaction-deprecated-in-api-23
                .addAction(R.drawable.prev_music, "", action)
                .addAction(R.drawable.vinil, "2", action/*2*/)
                .addAction(R.drawable.vinil, "3", action/*3*/)
                .setColor(Color.rgb(0, 0, 0))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.vinil);

        //Notification notification = new NotificationCompat.BigTextStyle(builder)
        //        .bigText(bigText).build();

        Notification notification = new NotificationCompat.BigPictureStyle(builder)
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.vinil)).build();

        manager.notify(101, notification);
    }
}
