package com.example.activities.composition;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.acg.lib.impl.AudioACG;
import com.acg.lib.listeners.ACGListeners;
import com.acg.lib.listeners.ResourceReadyListener;
import com.example.activities.good.AudioActivity;

import java.io.IOException;

/**
 * Activity which composes the AudioACG with a normal MediaPlayer
 */
public class ComposeAudioActivity extends AudioActivity {

    protected MediaPlayer mediaPlayer;
    protected Button playbackButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);

        // Set the audio playback button
        createPlaybackButton();
    }

    @Override
    protected void initializeACGs() {
        // Get the audio ACG
        audioACG = (AudioACG) getFragmentManager().findFragmentById(R.id.audio_acg_fragment_id);
    }

    /**
     * Set the audio playback button, which we make inactive until the audio is available
     */
    protected void createPlaybackButton() {
        playbackButton = (Button) findViewById(R.id.audio_playback_button_id);
        playbackButton.setActivated(false);
        playbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }

    protected int contentView() {
        return R.layout.activity_audio_compose;
    }

    @Override
    public ACGListeners buildACGListeners() {
        return new ACGListeners.Builder().withResourceReadyListener(audioACG, new ResourceReadyListener() {
            @Override
            public void onResourceReady() {
                try {
                    playbackButton.setEnabled(true);

                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                    }

                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(audioACG.getResource().getPath());
                    mediaPlayer.prepare();
                } catch (IOException | ACGResourceAccessException e) {
                    throw new RuntimeException("Failed to prepare media player", e);
                }
            }
        }).build();
    }
}
