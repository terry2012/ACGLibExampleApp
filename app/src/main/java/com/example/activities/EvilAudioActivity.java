package com.example.activities;

import android.os.Bundle;
import com.example.activities.good.AudioActivity;
import com.example.evildoers.EvilDoer;

/**
 * Base audio activity to make derivative malicious activity variants easier to write
 */
public abstract class EvilAudioActivity extends AudioActivity implements EvilActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Setup main view
        super.onCreate(savedInstanceState);

        // Do evil things (I really wish we had Java 8 Lambdas)
        for(EvilDoer evilDoer : evilDoers()) {
            evilDoer.doEvil(this);
        }
    }
}
