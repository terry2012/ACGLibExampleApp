package com.example.evildoers.clickjacking;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.example.evildoers.EvilDoer;

/**
 * Sets a button and its text to transparent
 */
public class TransparentViewEvilDoer implements EvilDoer {

    protected int viewToHideId;

    public TransparentViewEvilDoer(int viewToHideId) {
        this.viewToHideId = viewToHideId;
    }

    @Override
    public void doEvil(Activity activity) { // Not possible anymore with ViewWrapper (deliberately)
        View viewToHide = getViewToHide(activity);

        if (viewToHide instanceof TextView) {
            ((TextView) viewToHide).setTextColor(Color.TRANSPARENT);
        }

        viewToHide.setBackgroundColor(Color.TRANSPARENT);
    }

    protected View getViewToHide(Activity activity) {
        return activity.findViewById(viewToHideId);
    }
}
