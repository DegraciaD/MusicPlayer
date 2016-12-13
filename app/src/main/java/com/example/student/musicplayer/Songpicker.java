package com.example.student.musicplayer;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.action;
import static android.R.attr.category;
import static android.R.attr.filter;
import static android.R.attr.name;

public class Songpicker extends AppCompatActivity {
    public static SongObject bargainsSong;
    public static SongObject arduousSong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songpicker);
        int bargainsID = R.raw.bargainsinatuxedo;
        int arduousID = R.raw.arduoustask;




        MediaMetadataRetriever songInfo = new MediaMetadataRetriever();
        Uri filepath= Uri.parse("android.resource://" + getPackageName() + "/" + bargainsID);
        songInfo.setDataSource(this, filepath);

        String bargainsTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String bargainsArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

         songInfo = new MediaMetadataRetriever();
         filepath= Uri.parse("android.resource://" + getPackageName() + "/" + arduousID);
        songInfo.setDataSource(this, filepath);

        String arduousTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String  ardousArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);


        SongObject bargainsSong = new SongObject(bargainsID, bargainsTitle,bargainsArtist);
        SongObject arduousSong = new SongObject(arduousID, arduousTitle,ardousArtist);



    }
    public void playbargains(View view){
        String songID = String.valueOf(R.raw.bargainsinatuxedo);
        launchPlayer("bargains");
    }

    public void songpicker(View view){
        String songID = String.valueOf(R.raw.arduoustask);
        launchPlayer(songID);
    }

    public void launchPlayer(String songID){
        Intent launchSongPlayer = new Intent(Songpicker.this, MainActivity.class);
        launchSongPlayer.putExtra("songMessage", songID);
        startActivity(launchSongPlayer);
    }
}
