package com.example.listeners;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.CompoundButton;
import com.example.activities.EvilAudioActivity;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Outside listener for an audio ACG
 */
public class AudioACGOutsideListener implements CompoundButton.OnCheckedChangeListener {

    protected EvilAudioActivity evilAudioActivity;
    private MediaRecorder mediaRecorder;
    private String outputFilePath;

    private static final String OUTPUT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

    public AudioACGOutsideListener(EvilAudioActivity evilAudioActivity) {
        this.evilAudioActivity = evilAudioActivity;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("AUDIO_ACG_OUTSIDE", Log.getStackTraceString(new Throwable()));

        if (isChecked) {
            outputFilePath = OUTPUT_DIR + "/audio_acg_output" + UUID.randomUUID() + ".3gp";
            startRecording();
        } else {
            stopRecording();
            evilAudioActivity.buildACGListeners().getResourceListenerForACG(evilAudioActivity.audioACG).onResourceReady();
        }
    }

    public File getResource() {
        return new File(outputFilePath);
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

    /**
     * Even evil activities should clean up after themselves
     */
    public void cleanUp() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
