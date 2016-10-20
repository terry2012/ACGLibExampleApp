package com.example.activities.sparta;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.acg.EvilApp.R;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Simple bad activity that calls the media recorder directly instead of using the ACG at all.
 * This does not try to obfuscate anything.
 * The point of this activity is to make sure that SPARTA is detecting the correct flows.
 */
public class CallMediaRecorderActivity extends Activity {

    private MediaRecorder mediaRecorder;
    private String outputFilePath;
    protected Button playbackButton;
    protected MediaPlayer mediaPlayer;

    private static final String OUTPUT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_direct);

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

                    playbackButton.setEnabled(true);
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                        }

                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(file.getPath());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to prepare media player", e);
                    }
                }
            }
        };

        ToggleButton recordButton = (ToggleButton) findViewById(R.id.audio_direct_button_id);
        recordButton.setOnCheckedChangeListener(listener);

        // Set the audio playback button
        createPlaybackButton();
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

    @Override
    public void onStop() {
        super.onStop();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

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
}
