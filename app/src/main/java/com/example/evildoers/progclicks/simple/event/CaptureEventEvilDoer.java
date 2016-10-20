package com.example.evildoers.progclicks.simple.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Captures an event and plays it back later
 */
public class CaptureEventEvilDoer extends IndirectClickEvilDoer {

    protected int delayInMillis;

    protected static final ListeningScheduledExecutorService SCHEDULER = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor());

    public CaptureEventEvilDoer(int viewToClickId, int userExposedButtonId, int delayInMillis) {
        super(viewToClickId, userExposedButtonId);
        this.delayInMillis = delayInMillis;
    }

    @Override
    public void doEvil(final Activity activity) {
        final View viewToClick = getViewToClick(activity);
        final View userExposedView = activity.findViewById(userExposedButtonId);
        setPlaybackListener(activity, viewToClick, userExposedView);
    }

    /**
     * Set the listeners to play events back later
     */
    protected void setPlaybackListener(final Activity activity, final View viewToClick, final View userExposedView) {
        userExposedView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent event) {
                return scheduleEventPlayback(activity, viewToClick, event);
            }
        });
    }

    /**
     * Schedule the event to play back after a delay
     */
    protected boolean scheduleEventPlayback(final Activity activity, final View viewToClick, final MotionEvent event) {
        ListenableFuture future = SCHEDULER.schedule(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewToClick.dispatchTouchEvent(event);
                    }
                });
            }
        }, delayInMillis, TimeUnit.MILLISECONDS);

        // After another delay, remove the listener so we don't keep doing this forever
        Futures.addCallback(future, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                SCHEDULER.schedule(new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewToClick.setOnTouchListener(null);
                            }
                        });
                    }
                }, delayInMillis, TimeUnit.MILLISECONDS);
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });

        return true;
    }
}
