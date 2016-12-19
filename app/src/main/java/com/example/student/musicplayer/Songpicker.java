package com.example.student.musicplayer;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.action;
import static android.R.attr.category;
import static android.R.attr.filter;
import static android.R.attr.name;

public class Songpicker extends AppCompatActivity {
    public static SongObject bargainsSong;
    public static SongObject arduousSong;
    public static int[] songIDs;
    public static ArrayList<SongObject> songList ;
    public static int songID;
    public static MediaMetadataRetriever songInfo = new MediaMetadataRetriever();
    private SongAdapter mySongAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songpicker);
        int bargainsID = R.raw.bargainsinatuxedo;
        int arduousID = R.raw.arduoustask;
        songIDs = new int[10];
        songIDs[0] = R.raw.bargainsinatuxedo;
        songIDs[1] = R.raw.glaringlyablaze;
        songIDs[2] = R.raw.aqualounge;
        songIDs[3] = R.raw.arduoustask;
        songIDs[4] = R.raw.blowtothehead;
        songIDs[5] = R.raw.fightingcombat;
        songIDs[6] = R.raw.letitrip;
        songIDs[7] = R.raw.losingaccusations;
        songIDs[8] = R.raw.nightmarestogo;
        songIDs[9] = R.raw.rabidcourage;

        for( int i=0;i<songIDs.length;i++) {
            songID = songIDs[i];

            MediaMetadataRetriever songInfo = new MediaMetadataRetriever();
            Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + songID);
            songInfo.setDataSource(this, mediaPath);

            String songTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String songArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

            songList.add( new SongObject(songID, songTitle, songArtist) );
        }

        mySongAdapter = new SongAdapter(this, songList);

        ListView listView = (ListView) findViewById(R.id.songListView);
        listView.setAdapter(mySongAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                launchPlayer(String.valueOf(position));
            }
        });

    }
    public void playbargains(View view){
        String songID = String.valueOf(R.raw.bargainsinatuxedo);
        launchPlayer(songID);
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
