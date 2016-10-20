package com.example.evildoers.clickjacking;

import android.app.Activity;
import android.view.View;
import android.widget.ToggleButton;
import com.google.common.base.Optional;

import static android.view.View.FOCUS_DOWN;

/**
 * Modifies the basic information (e.g. text, size) of a toggle button
 * This will be broken for now with the validated frame, but we can see whether or not there are ways around it by playing with this
 */
public class ModifyToggleButtonEvilDoer extends ModifyViewEvilDoer { // Not possible anymore with ViewWrapper (deliberately)

    protected Optional<String> textOn = Optional.absent();
    protected Optional<String> textOff = Optional.absent();

    public ModifyToggleButtonEvilDoer(int buttonToModifyId, String text) {
        super(buttonToModifyId, text);
    }

    public ModifyToggleButtonEvilDoer(int buttonToModifyId, int width, int height) {
        super(buttonToModifyId, width, height);
    }

    public ModifyToggleButtonEvilDoer(int buttonToModifyId, String text, String textOn, String textOff, int width, int height) {
        super(buttonToModifyId, text, width, height);
        this.textOn = Optional.of(textOn);
        this.textOff = Optional.of(textOff);
    }

    @Override
    public void doEvil(Activity activity) {
        super.doEvil(activity);

        ToggleButton buttonToModify = getView(activity);

        if (textOn.isPresent()) {
            buttonToModify.setTextOn(textOn.get());
        } else if (text.isPresent()) {
            buttonToModify.setTextOn(text.get());
        }

        if (textOff.isPresent()) {
            buttonToModify.setTextOff(textOff.get());
        } else if (text.isPresent()) {
            buttonToModify.setTextOff(text.get());
        }
    }

    protected ToggleButton getView(Activity activity) {
        View view = activity.findViewById(viewToModifyId);
        return (ToggleButton) view.getFocusables(FOCUS_DOWN).get(0); // Hopefully not possible when validation is on
    }
}
