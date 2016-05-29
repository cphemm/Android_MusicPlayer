package com.example.carolynhemmings.soundboxmusicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button playButton;
    private Button prevButton;
    private Button nextButton;
    private MediaPlayer mediaPlayer;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplication(), R.raw.yankee_doodle);

        text = (TextView) findViewById(R.id.artistName);

        playButton = (Button) findViewById(R.id.playButton);
        prevButton = (Button) findViewById(R.id.prevButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        playButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.playButton:
                if(mediaPlayer.isPlaying()) {
                    pauseMusic();
                }else {
                    playMusic();
                }
                break;
            case R.id.prevButton:
                prevMusic();
                break;
            case R.id.nextButton:
                nextMusic();
                break;
        }
    }

    public void playMusic() {
        if(mediaPlayer != null) {
            mediaPlayer.start();
            text.setText("Music Playing now...");
            playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
        }
    }

    public void pauseMusic() {
        if(mediaPlayer != null) {
            mediaPlayer.pause();
            text.setText("Music has Paused!");
            playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
        }
    }

    public void prevMusic() {
        if(mediaPlayer != null) {
            mediaPlayer.seekTo(-5000);
            text.setText("You have gone backwards 5 seconds!");
            prevButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_previous));
        }
    }

    public void nextMusic() {
        if(mediaPlayer != null) {
            mediaPlayer.seekTo(5000);
            text.setText("You have skipped forward 5 seconds!");
            nextButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_next));
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }
}
