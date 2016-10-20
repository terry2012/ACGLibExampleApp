package com.example.activities;

import android.os.Bundle;
import com.example.activities.good.LocationActivity;
import com.example.evildoers.EvilDoer;

/**
 * Base location activity to make derivative malicious activity variants easier to write
 */
public abstract class EvilLocationActivity extends LocationActivity implements EvilActivity {

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
