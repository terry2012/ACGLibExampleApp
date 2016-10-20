package com.example.evildoers.progclicks.simple;

import android.app.Activity;
import android.view.View;
import com.example.evildoers.EvilDoer;

/**
 * Programmatically click a view
 */
public class ProgClickEvilDoer implements EvilDoer {

    protected int viewToClickId;

    public ProgClickEvilDoer(int viewToClickId) {
        this.viewToClickId = viewToClickId;
    }

    @Override
    public void doEvil(Activity activity) {
        // Programmatically click a view
        View viewToClick = getViewToClick(activity);
        viewToClick.performClick();
    }

    protected View getViewToClick(Activity activity) {
        return activity.findViewById(viewToClickId);
    }
}
