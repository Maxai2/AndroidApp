package com.example.karaoke;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList text = new ArrayList();
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        while (true) {
            text.add();
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.belle_minus);
        mediaPlayer.setLooping(true);

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

    public void Start(View view) {
        Button btn = (Button)view;

        btn.setText("Next");
        mediaPlayer.start();
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
