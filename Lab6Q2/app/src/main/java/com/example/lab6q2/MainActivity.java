package com.example.lab6q2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView songList;
    TextView tvTitle;
    Button btnPlay, btnPause;
    MediaPlayer mediaPlayer;

    String[] songs = {"Song 1", "Song 2"};
    int currentSongIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songList = findViewById(R.id.songList);
        tvTitle = findViewById(R.id.tvTitle);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        songList.setAdapter(adapter);

        songList.setOnItemClickListener((parent, view, position, id) -> {
            currentSongIndex = position;
            playSelectedSong(position);
        });

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                tvTitle.setText("Now Playing: " + songs[currentSongIndex]);
            }
        });

        btnPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                tvTitle.setText("Paused: " + songs[currentSongIndex]);
            }
        });
    }

    private void playSelectedSong(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        if (position == 0) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song1);
        } else if (position == 1) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.song2);
        }

        if (mediaPlayer != null) {
            mediaPlayer.start();
            tvTitle.setText("Now Playing: " + songs[position]);
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
