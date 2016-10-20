package com.example.evildoers.progclicks.simple;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

/**
 * Click a button indirectly, through a listener to another button
 */
public class IndirectClickEvilDoer extends ProgClickEvilDoer { // Programmatic versions don't work (deliberately) anymore because only way is through TouchEvent

    protected int userExposedButtonId;

    public IndirectClickEvilDoer(int viewToClickId, int userExposedButtonId) {
        super(viewToClickId);
        this.userExposedButtonId = userExposedButtonId;
    }

    @Override
    public void doEvil(Activity activity) {
        // Let's hide the ACG button first
        final View viewToClick = getViewToClick(activity);
        viewToClick.setVisibility(View.INVISIBLE);

        // Now set the listeners for the disguised malicious button
        Button button = (Button) activity.findViewById(userExposedButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewToClick.performClick();
            }
        });
    }
}
