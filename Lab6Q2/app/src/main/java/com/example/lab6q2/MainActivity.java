package com.example.lab6q2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //put songlist , song1,song 2 in res/raw/ folder , eg res/raw/song1.mp3
    ListView songList;
    MediaPlayer mediaPlayer;

    String[] songs = {"Song 1", "Song 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //songList = findViewById(R.id.songList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        songList.setAdapter(adapter);

        songList.setOnItemClickListener((parent, view, position, id) -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            if (position == 0) {
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song1);
            } else if (position == 1) {
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song2);
            }

            mediaPlayer.start();
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
