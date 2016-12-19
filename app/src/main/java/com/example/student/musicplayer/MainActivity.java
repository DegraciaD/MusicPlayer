package com.example.student.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.attr.author;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer song1;
    public Button playButtonVar;
    public Button pauseButtonVar;
    public Button stopButtonVar;

    public TextView endTimeViewVar;
    public TextView currentTimeViewVar;
    public TextView title;
    public TextView author;

    public int currentTimeMS;
    public int finalTimeMS;
    public int endMinutes;
    public int endSeconds;
    public int currentMinutes;
    public int currentSeconds;
    public double endTimeMS;
    public String songTitle;
    public String songArtist;
    public TextView songTitleViewVar;
    public TextView songArtistViewVar;
    public SongObject thisSong;
    public String songID;
    public int songNumber;
    private SeekBar mySongBarVar;
    public int seekTime;


    private Handler myHandler = new Handler();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent thisIntent = getIntent();

    private void playNewSong(int position) {
        if (song1 != null) {
            song1.stop();
            song1.release();
            song1 = null;
        }
        song1 = MediaPlayer.create(this, thisSong.songID);
        song1.seekTo(0);


    }

    thisSong=Songpicker.songList.get(position);

    songNumber=Integer.parseInt(thisIntent.getStringExtra("songMessage"));
    thisSong=Songpicker.songList.get(songNumber);

    if(song1!=null)

    {
        song1.stop();
        song1.release();
        song1 = null;
    }

    songTitle=thisSong.title;
    songArtist=thisSong.artist;
    title=(TextView)

    findViewById(R.id.songTitleView);

    author=(TextView)

    findViewById(R.id.songArtistView);

    title=(TextView)

    findViewById(R.id.textView4);

    author=(TextView)

    findViewById(R.id.textView);

    playButtonVar=(Button)

    findViewById(R.id.txtVw_playbutton2);

    pauseButtonVar=(Button)

    findViewById(R.id.txtVw_pausebutton);

    stopButtonVar=(Button)

    findViewById(R.id.txtVw_stopbutton);

    TextView songTitleViewVar = (TextView) findViewById(R.id.text);
    endTimeViewVar=(TextView)

    findViewById(R.id.txtVw_songtext);

    currentTimeViewVar=(TextView)

    findViewById(R.id.txtVw_songtime);

    finalTimeMS=song1.getDuration();
    songTitleViewVar=(TextView)

    findViewById(R.id.songNameView);

    songArtistViewVar=(TextView)

    findViewById(R.id.songArtist);

    currentTimeMS=song1.getCurrentPosition();
    endMinutes=(int)(finalTimeMS/1000/60);
    endSeconds=((int)(finalTimeMS/1000))%60;
    currentMinutes=(int)(currentTimeMS/1000/60);
    currentSeconds=((int)(currentTimeMS/1000))%60;
    endTimeViewVar.setText(endMinutes+" min, "+endSeconds+" sec");
    currentTimeViewVar.setText(currentMinutes+" min, "+currentSeconds+" sec");
    myHandler.postDelayed(UpdateSongTime,100);
    currentTimeMS=song1.getCurrentPosition();
    endSeconds=(int)(endTimeMS/1000)%60;
    endMinutes=(int)(endTimeMS/1000)%60;
    mySongBarVar=(SeekBar)

    findViewById(R.id.txtVw_seekBar1);

    mySongBarVar.setMax((int)finalTimeMS);
    mySongBarVar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
        seekTime = progress;
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){

    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){
        song1.seekTo(seekTime);
        currentTimeMS = seekTime;

    }
    }

    );


    songArtist=thisSong.title;
    songTitle=thisSong.artist;
    songTitleViewVar.setText(songTitle);
    songArtistViewVar.setText(songArtist);

    playSong();

}

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {

            currentTimeMS = song1.getCurrentPosition();
            currentMinutes = (int) (currentTimeMS / 1000 / 60);
            currentSeconds = ((int) (currentTimeMS / 1000)) % 60;
            currentTimeViewVar.setText(currentMinutes + " min, " + currentSeconds + " sec");
            myHandler.postDelayed(this, 100);
            mySongBarVar.setProgress((int) currentTimeMS);


        }
    };

    public void playSongclick(View view) {
        playSong();
    }

    public void playSong(View view) {
        playButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(true);
        pauseButtonVar.setEnabled(true);
        song1.start();
        Context context = getApplicationContext();
        CharSequence text = "Play";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
    }

    public void pauseSong(View view) {
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        song1.pause();
        Context context = getApplicationContext();
        CharSequence text = "Pause";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();

    }

    public void stopSong(View view) {
        pauseButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        song1.pause();
        Context context = getApplicationContext();
        CharSequence text = "Stop";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
        song1.seekTo((int) (0000));


    }

    public void rewindSong(View view) {

        if (currentTimeMS > 5000) {

            song1.seekTo(currentTimeMS - 5000);
        }

        Context context = getApplicationContext();
        CharSequence text = "rewind";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
    }

    public void fastforwardSong(View view) {

        if (currentTimeMS < finalTimeMS - 5000) {

            song1.seekTo(currentTimeMS + 5000);
        }

        Context context = getApplicationContext();
        CharSequence text = "fastforward";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
    }
}
