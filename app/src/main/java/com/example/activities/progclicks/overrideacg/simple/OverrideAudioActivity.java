package com.example.activities.progclicks.overrideacg.simple;

import android.support.annotation.NonNull;
import com.acg.EvilApp.R;
import com.acg.lib.ACGResourceAccessException;
import com.acg.lib.listeners.ACGListeners;
import com.acg.lib.listeners.ResourceReadyListener;
import com.example.activities.progclicks.simple.ProgClickAudioActivity;
import com.example.evildoers.EvilDoer;
import com.example.evildoers.progclicks.simple.OverrideACGEvilDoer;
import com.example.listeners.AudioACGOutsideListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Audio activity that overrides the ACG listener to disguise the flow and programmatically clicks it
 */
public class OverrideAudioActivity extends ProgClickAudioActivity {

    protected AudioACGOutsideListener acgListener;

    @NonNull
    @Override
    public Collection<EvilDoer> evilDoers() {
        List<EvilDoer> evilDoers = new ArrayList<>();
        evilDoers.add(buildOverrideACGEvilDoer());
        evilDoers.addAll(super.evilDoers());
        return evilDoers;
    }

    protected EvilDoer buildOverrideACGEvilDoer() {
        acgListener = new AudioACGOutsideListener(this);
        return new OverrideACGEvilDoer(R.id.audio_acg_button_id, acgListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(acgListener != null) {
            acgListener.cleanUp();
        }
    }

    @Override
    public ACGListeners buildACGListeners() {
        return new ACGListeners.Builder().withResourceReadyListener(audioACG, new ResourceReadyListener() {
            @Override
            public void onResourceReady() {
                playAudioACG.passInput(acgListener.getResource());
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
