package com.example.evildoers.clickjacking;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.evildoers.EvilDoer;
import com.google.common.base.Optional;

/**
 * Modifies the basic information (e.g. text, size) of a button
 */
public class ModifyViewEvilDoer implements EvilDoer {

    protected int viewToModifyId;
    protected Optional<String> text = Optional.absent();
    protected Optional<Integer> width = Optional.absent();
    protected Optional<Integer> height = Optional.absent();

    public ModifyViewEvilDoer(int viewToModifyId, String text) {
        this.viewToModifyId = viewToModifyId;
        this.text = Optional.of(text);
    }

    public ModifyViewEvilDoer(int viewToModifyId, int width, int height) {
        this.viewToModifyId = viewToModifyId;
        this.width = Optional.of(width);
        this.height = Optional.of(height);
    }

    public ModifyViewEvilDoer(int viewToModifyId, String text, int width, int height) {
        this.viewToModifyId = viewToModifyId;
        this.text = Optional.of(text);
        this.width = Optional.of(width);
        this.height = Optional.of(height);
    }

    @Override
    public void doEvil(Activity activity) { // Not possible anymore with ViewWrapper (deliberately)
        View viewToModify = getView(activity);

        if (viewToModify instanceof TextView && text.isPresent()) {
            TextView textView = ((TextView) viewToModify);

            textView.setText(text.get());

            if (width.isPresent()) {
                textView.setMinWidth(width.get());
                textView.setWidth(width.get());
            }

            if (height.isPresent()) {
                textView.setMinHeight(height.get());
                textView.setHeight(height.get());
            }
        }

        if (width.isPresent() || height.isPresent()) {
            if (viewToModify instanceof TextView) {
                ((TextView) viewToModify).setTextSize(4); //shrink the text, can make this a parameter too if this needs to be more flexible
            }

            viewToModify.setLayoutParams(new FrameLayout.LayoutParams(width.or(FrameLayout.LayoutParams.WRAP_CONTENT), height.or(FrameLayout.LayoutParams.WRAP_CONTENT)));
        }
    }

    protected View getView(Activity activity) {
        return activity.findViewById(viewToModifyId);
    }
}
