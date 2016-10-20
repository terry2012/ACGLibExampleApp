package com.example.activities.good;

import android.app.Activity;
import android.os.Bundle;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.acg.lib.impl.AudioACG;
import com.acg.lib.impl.PlayAudioACG;
import com.acg.lib.listeners.ACGActivity;
import com.acg.lib.listeners.ACGListeners;
import com.acg.lib.listeners.ResourceReadyListener;

/**
 * Good audio activity, for use as a reference
 */
public class AudioActivity extends Activity implements ACGActivity {

    public AudioACG audioACG; // Public to make malicious activities easier to implement and then catch
    protected PlayAudioACG playAudioACG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);
        setContentView(contentView());
        initializeACGs();
    }

    protected void initializeACGs() {
        audioACG = (AudioACG) getFragmentManager().findFragmentById(R.id.audio_acg_fragment_id);
        playAudioACG = (PlayAudioACG) getFragmentManager().findFragmentById(R.id.play_audio_acg_fragment_id);
    }

    protected int contentView() {
        return R.layout.activity_audio;
    }

    @Override
    public ACGListeners buildACGListeners() {
        return new ACGListeners.Builder().withResourceReadyListener(audioACG, new ResourceReadyListener() {
            @Override
            public void onResourceReady() {
                try {
                    playAudioACG.passInput(audioACG.getResource()); // TODO in library, can make something to glue two ACGs together this way
                } catch (ACGResourceAccessException e) {
                    throw new RuntimeException("Unexpected error getting file");
                }
            }
        }).withResourceReadyListener(playAudioACG, new ResourceReadyListener() {
            @Override
            public void onResourceReady() {
                try {
                    playAudioACG.getResource();
                } catch (ACGResourceAccessException e) {
                    throw new RuntimeException("Unexpected error playing file");
                }
            }
        }).build();
    }
}
