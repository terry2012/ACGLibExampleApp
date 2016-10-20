package com.example.evildoers.progclicks.simple.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

/**
 * Modifies an event in a different location
 */
public class ModifyEventEvilDoer extends IndirectClickEvilDoer {

    public ModifyEventEvilDoer(int viewToClickId, int userExposedButtonId) {
        super(viewToClickId, userExposedButtonId);
    }

    @Override
    public void doEvil(Activity activity) {
        final View viewToClick = getViewToClick(activity);

        // Now set the listeners for the disguised malicious button
        Button button = (Button) activity.findViewById(userExposedButtonId);
        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                event.offsetLocation(0, -100);
                return viewToClick.dispatchTouchEvent(event);
            }
        });
    }
}

