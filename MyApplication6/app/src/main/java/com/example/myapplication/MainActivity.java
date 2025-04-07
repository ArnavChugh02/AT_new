package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView timeIndicator;
    private Button buttonPlayPause, buttonForward, buttonRewind;
    private boolean isPlaying = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        buttonPlayPause = findViewById(R.id.buttonPlayPause);
        buttonForward = findViewById(R.id.buttonForward);
        buttonRewind = findViewById(R.id.buttonRewind);
        seekBar = findViewById(R.id.seekBar);
        timeIndicator = findViewById(R.id.timeIndicator);

        // Set up the media player (using a sample audio file)
        //mediaPlayer = MediaPlayer.create(this, R.raw.sample2);  // Replace with your audio resource

        // Set the seek bar max value to the media duration
        seekBar.setMax(mediaPlayer.getDuration());

        // Set up the media player update logic
        mediaPlayer.setOnPreparedListener(mp -> {
            seekBar.setMax(mp.getDuration());
            updateSeekBar();
        });

        // Button to play/pause the media
        buttonPlayPause.setOnClickListener(v -> togglePlayPause());

        // Button to fast forward the media by 10 seconds
        buttonForward.setOnClickListener(v -> fastForward());

        // Button to rewind the media by 10 seconds
        buttonRewind.setOnClickListener(v -> rewind());

        // Seek bar change listener to update the media position
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Toggle between play and pause
    private void togglePlayPause() {
        if (isPlaying) {
            mediaPlayer.pause();
            buttonPlayPause.setText("Play");
        } else {
            mediaPlayer.start();
            buttonPlayPause.setText("Pause");
            updateSeekBar();
        }
        isPlaying = !isPlaying;
    }

    // Fast forward the media by 10 seconds
    private void fastForward() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int forwardPosition = currentPosition + 10000; // 10 seconds forward
        if (forwardPosition < mediaPlayer.getDuration()) {
            mediaPlayer.seekTo(forwardPosition);
            seekBar.setProgress(forwardPosition);
        } else {
            mediaPlayer.seekTo(mediaPlayer.getDuration());
            seekBar.setProgress(mediaPlayer.getDuration());
        }
    }

    // Rewind the media by 10 seconds
    private void rewind() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int rewindPosition = currentPosition - 10000; // 10 seconds rewind
        if (rewindPosition > 0) {
            mediaPlayer.seekTo(rewindPosition);
            seekBar.setProgress(rewindPosition);
        } else {
            mediaPlayer.seekTo(0);
            seekBar.setProgress(0);
        }
    }

    // Update the seek bar and time indicator while the media is playing
    private void updateSeekBar() {
        Runnable updateRunnable = new Runnable() {
            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                seekBar.setProgress(currentPosition);

                // Update time indicator (current time / total time)
                int minutes = currentPosition / 1000 / 60;
                int seconds = (currentPosition / 1000) % 60;
                timeIndicator.setText(String.format("%02d:%02d / %02d:%02d", minutes, seconds,
                        mediaPlayer.getDuration() / 1000 / 60, (mediaPlayer.getDuration() / 1000) % 60));

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(updateRunnable, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
