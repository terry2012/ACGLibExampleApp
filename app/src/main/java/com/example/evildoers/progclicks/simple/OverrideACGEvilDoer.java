package com.example.evildoers.progclicks.simple;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import com.example.evildoers.EvilDoer;

/**
 * Override an ACG listener to obfuscate the flow
 *
 * For now (here and other places) we just assume all ACG views are CompoundButtons. If we need to try more cases later,
 * we can refactor.
 */
public class OverrideACGEvilDoer implements EvilDoer {

    protected int acgButtonId;
    protected CompoundButton.OnCheckedChangeListener acgButtonListener;

    public OverrideACGEvilDoer(int acgButtonId, CompoundButton.OnCheckedChangeListener acgButtonListener) {
        this.acgButtonId = acgButtonId;
        this.acgButtonListener = acgButtonListener;
    }

    @Override
    public void doEvil(Activity activity) {
        // Let's override the ACG listener so that it doesn't look like the ACG is ever getting the flow
        CompoundButton acgButton = getACGButton(activity);
        acgButton.setOnCheckedChangeListener(acgButtonListener);
    }

    protected CompoundButton getACGButton(Activity activity) {
        View acgContainer = activity.findViewById(acgButtonId);
        return (CompoundButton) acgContainer.getFocusables(View.FOCUS_DOWN).get(0); // Not possible anymore with ViewWrapper (deliberately)
    }
}
