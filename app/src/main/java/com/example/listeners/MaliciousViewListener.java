package com.example.listeners;

import android.view.View;

/**
 * Outside listener for a disguised view that triggers a view, to analyze more complex malicious flow
 */
public class MaliciousViewListener implements View.OnClickListener {

    View viewToClick;

    public MaliciousViewListener(View viewToClick) {
        this.viewToClick = viewToClick;
    }

    @Override
    public void onClick(View v) {
        viewToClick.performClick();
    }
}
