package com.example.student.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song1;

    public Button pauseButtonVar;

    public Button playButtonVar;

    public Button stopButtonVar;

    public int currentTimeMS;
    public int finalTimeMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        song1 = MediaPlayer.create(this, R.raw.bargainsinatuxedo);
        playButtonVar = (Button) findViewById(R.id.txtVw_playbutton2);
        pauseButtonVar = (Button) findViewById(R.id.txtVw_pausebutton);
        stopButtonVar = (Button) findViewById(R.id.txtVw_stopbutton);
        finalTimeMS = song1.getDuration();
        currentTimeMS = song1.getCurrentPosition();


    }

    public void playSong(View view) {
        playButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(true);
        pauseButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "your music is playing now";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
        song1.start();
    }

    public void pauseSong(View view) {
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "your music is Paused now";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
        song1.pause();
    }

    public void stopSong(View view) {
        pauseButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "your music is stopped now";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
        song1.pause();
        song1.seekTo( (int) (currentTimeMS + 0000) );

    }

    public void fastforwardSong(View view) {
        Context context = getApplicationContext();
        CharSequence text = "your music is  fast-forwarding";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
         song1.seekTo( (int) (currentTimeMS + 5000) );

    }

    public void rewindSong(View view) {
        Context context = getApplicationContext();
        CharSequence text = "your music is rewinding";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
        song1.seekTo((int) (currentTimeMS - 5000));
    }
}