package com.example.activities.composition;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.acg.lib.impl.PlayAudioACG;
import com.acg.lib.listeners.ACGListeners;
import com.acg.lib.listeners.ResourceReadyListener;
import com.example.activities.good.AudioActivity;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Activity which composes the PlayAudioACG with a normal MediaRecorder
 */
public class ComposePlayAudioActivity extends AudioActivity {

    private MediaRecorder mediaRecorder;
    private String outputFilePath;

    private static final String OUTPUT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);

        // Create button listener
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    outputFilePath = OUTPUT_DIR + "/audio_acg_output" + UUID.randomUUID() + ".3gp";
                    startRecording();
                } else {
                    stopRecording();
                    File file = new File(outputFilePath);
                    playAudioACG.passInput(file);
                }
            }
        };

        ToggleButton recordButton = (ToggleButton) findViewById(R.id.audio_direct_button_id);
        recordButton.setOnCheckedChangeListener(listener);
    }

    @Override
    protected void initializeACGs() {
        // Get the play audio ACG
        playAudioACG = (PlayAudioACG) getFragmentManager().findFragmentById(R.id.play_audio_acg_fragment_id);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    /**
     * Record audio from the speaker and put it into a local cache
     */
    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(outputFilePath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            throw new RuntimeException("Failed to prepare the media recorder", e);
        }

        mediaRecorder.start();
    }

    /**
     * Stop recording audio
     * It's good Android practice to release the recorder after using it, so you have to create a new one each time
     */
    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    protected int contentView() {
        return R.layout.activity_play_audio_compose;
    }

    @Override
    public ACGListeners buildACGListeners() {
        return new ACGListeners.Builder().withResourceReadyListener(playAudioACG, new ResourceReadyListener() {
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
