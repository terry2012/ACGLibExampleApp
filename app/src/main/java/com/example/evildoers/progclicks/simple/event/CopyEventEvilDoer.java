package com.example.evildoers.progclicks.simple.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Copies an event and plays it back later
 */
public class CopyEventEvilDoer extends CaptureEventEvilDoer {

    public CopyEventEvilDoer(int viewToClickId, int userExposedButtonId, int delayInMillis) {
        super(viewToClickId, userExposedButtonId, delayInMillis);
    }

    /**
     * Set the listeners to copy events and play them back later
     */
    protected void setPlaybackListener(final Activity activity, final View viewToClick, final View userExposedView) {
        userExposedView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent event) {
                return scheduleEventPlayback(activity, viewToClick, MotionEvent.obtain(event));
            }
        });
    }
}
