package com.example.evildoers;

import android.app.Activity;

/**
 * Represents a way to do evil for an activity, essentially an evil strategy
 */
public interface EvilDoer {

    /**
     * Do evil things for an activity
     */
    void doEvil(Activity activity);
}
