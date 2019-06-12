package com.example.karaoke;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String text = "\nСвет озарил мою больную душу\n" +
            "Нет, твой покой я страстью не нарушу\n" +
            "Бред. Полночный бред терзает сердце мне опять\n" +
            " О, Эсмеральда, я посмел тебя желать\n" +
            " Мой тяжкий крест - уродства вечная печать\n" +
            " Я состраданье за любовь готов принять\n" +
            " Нет, горбун отверженный с проклятьем на челе\n" +
            " Я никогда не буду счастлив на земле\n" +
            " И после смерти мне не обрести покой\n" +
            " Я душу дьяволу продам за ночь с тобой\n" +
            "Рай. Обещают рай твои обьятья\n" +
            " Дай мне надежду. О, мое проклятье\n" +
            " Знай греховных мыслей мне сладка слепая власть\n" +
            " Безумец - прежде я не знал, что значит страсть\n" +
            " Распутной девкой, словно бесом одержим\n" +
            " Цыганка дерзкая мою сгубила жизнь\n" +
            " Жаль. Судьбы насмешкою я в рясу облачен\n" +
            " На муки адские навеки обречен\n" +
            " И после смерти мне не обрести покой\n" +
            " Я душу дьяволу продам за ночь с тобой\n" +
            "Сон. Светлый, счастья сон мой, Эсмеральда\n" +
            " Стон, грешной страсти стон мой, Эсмеральда\n" +
            " Он сорвался с губ и покатился камнем вниз\n" +
            " Разбилось сердце белокурой Флёр-де-Лиз\n" +
            " Святая дева ты не в силах мне помочь\n" +
            " Любви запретной не дано мне превозмочь\n" +
            " Стой. Не покидай меня безумная мечта\n" +
            " В раба мужчину превращает красота\n" +
            " и после смерти мне не обрести покой\n" +
            " Я душу дьяволу продам за ночь с тобой\n" +
            "И днем и ночью лишь она передо мной\n" +
            " И не Мадонне я молюсь, а ей одной\n" +
            " Стой. Не покидай меня безумная мечта\n" +
            " В раба мужчину превращает красота\n" +
            " И после смерти мне не обрести покой\n" +
            " Я душу дьяволу продам за ночь с тобой\n" +
            " За ночь с тобой\n" +
            "\n";

    MediaPlayer mediaPlayer;
    ArrayList textArr = new ArrayList();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pa).setEnabled(false);
        findViewById(R.id.stop).setEnabled(false);

        mediaPlayer = MediaPlayer.create(this, R.raw.belle_minus);
        mediaPlayer.setLooping(true);

        int start = 0, end = 0, count = 0;

        while (true) {
            start = end;
            end = text.indexOf("\n", start + 1);
            textArr.add(text.substring(start + 1, end));

            if (textArr.get(count++).toString().equals("")) {
                textArr.remove(count - 1);
                break;
            }
        }

//        VideoView videoView =(VideoView)findViewById(R.id.videoView);
//
//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(videoView);
//
//        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/video/MOV.mp4");
//
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();
    }

    public void onClick(View view) {
        Button btn = (Button)view;

        switch (btn.getId()) {
            case R.id.st:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.belle_minus);
                }
                mediaPlayer.start();
                findViewById(R.id.st).setEnabled(false);
                findViewById(R.id.pa).setEnabled(true);
                findViewById(R.id.stop).setEnabled(true);
                findViewById(R.id.ne).setEnabled(true);
                break;
            case R.id.pa:
                mediaPlayer.pause();
                findViewById(R.id.pa).setEnabled(false);
                findViewById(R.id.st).setEnabled(true);
                findViewById(R.id.ne).setEnabled(false);
                break;
            case R.id.stop:
                mediaPlayer.stop();
                mediaPlayer = null;
                findViewById(R.id.stop).setEnabled(false);
                findViewById(R.id.st).setEnabled(true);
                findViewById(R.id.pa).setEnabled(false);
                findViewById(R.id.ne).setEnabled(false);
                index = 0;
                break;
            case R.id.ne:
                Toast t = Toast.makeText(this, textArr.get(index++).toString(), Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                break;
        }

        if (index == textArr.size()) {
            index = 0;
        }

    }
}

/*
*

Свет озарил мою больную душу
Нет, твой покой я страстью не нарушу
Бред. Полночный бред терзает сердце мне опять
 О, Эсмеральда, я посмел тебя желать
 Мой тяжкий крест - уродства вечная печать
 Я состраданье за любовь готов принять
 Нет, горбун отверженный с проклятьем на челе
 Я никогда не буду счастлив на земле
 И после смерти мне не обрести покой
 Я душу дьяволу продам за ночь с тобой

Рай. Обещают рай твои обьятья
 Дай мне надежду. О, мое проклятье
 Знай греховных мыслей мне сладка слепая власть
 Безумец - прежде я не знал, что значит страсть
 Распутной девкой, словно бесом одержим
 Цыганка дерзкая мою сгубила жизнь
 Жаль. Судьбы насмешкою я в рясу облачен
 На муки адские навеки обречен
 И после смерти мне не обрести покой
 Я душу дьяволу продам за ночь с тобой

Сон. Светлый, счастья сон мой, Эсмеральда
 Стон, грешной страсти стон мой, Эсмеральда
 Он сорвался с губ и покатился камнем вниз
 Разбилось сердце белокурой Флёр-де-Лиз
 Святая дева ты не в силах мне помочь
 Любви запретной не дано мне превозмочь
 Стой. Не покидай меня безумная мечта
 В раба мужчину превращает красота
 и после смерти мне не обрести покой
 Я душу дьяволу продам за ночь с тобой

И днем и ночью лишь она передо мной
 И не Мадонне я молюсь, а ей одной
 Стой. Не покидай меня безумная мечта
 В раба мужчину превращает красота
 И после смерти мне не обрести покой
 Я душу дьяволу продам за ночь с тобой
 За ночь с тобой


* */
