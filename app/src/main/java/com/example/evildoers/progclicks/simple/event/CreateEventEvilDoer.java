package com.example.evildoers.progclicks.simple.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.example.evildoers.progclicks.simple.ProgClickEvilDoer;

import static android.os.SystemClock.uptimeMillis;
import static android.view.InputDevice.SOURCE_TOUCHSCREEN;
import static android.view.MotionEvent.*;

/**
 * Create and dispatch a MotionEvent
 */
public class CreateEventEvilDoer extends ProgClickEvilDoer {

    public CreateEventEvilDoer(int viewToClickId) {
        super(viewToClickId);
    }

    @Override
    public void doEvil(Activity activity) {
        // Programmatically click a view
        View viewToClick = getViewToClick(activity);

        // Create pointers
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = 0;

        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.setAxisValue(MotionEvent.AXIS_X, viewToClick.getX());
        pointerCoords.setAxisValue(MotionEvent.AXIS_Y, viewToClick.getY());

        // Create events
        MotionEvent downEvent = obtain(
                uptimeMillis(),
                uptimeMillis(),
                ACTION_DOWN,
                1,
                new MotionEvent.PointerProperties[]{pointerProperties},
                new MotionEvent.PointerCoords[]{pointerCoords},
                0,
                0,
                1.0f,
                1.0f,
                7,
                0x0,
                SOURCE_TOUCHSCREEN,
                0x0
        );

        MotionEvent moveEvent = obtain(
                uptimeMillis(),
                uptimeMillis(),
                ACTION_MOVE,
                1,
                new MotionEvent.PointerProperties[]{pointerProperties},
                new MotionEvent.PointerCoords[]{pointerCoords},
                0,
                0,
                1.0f,
                1.0f,
                7,
                0x0,
                SOURCE_TOUCHSCREEN,
                0x0
        );

        MotionEvent upEvent = obtain(
                uptimeMillis(),
                uptimeMillis(),
                ACTION_UP,
                1,
                new MotionEvent.PointerProperties[]{pointerProperties},
                new MotionEvent.PointerCoords[]{pointerCoords},
                0,
                0,
                1.0f,
                1.0f,
                7,
                0x0,
                SOURCE_TOUCHSCREEN,
                0x0
        );

        // Dispatch events
        viewToClick.dispatchGenericMotionEvent(downEvent);
        viewToClick.dispatchGenericMotionEvent(moveEvent);
        viewToClick.dispatchGenericMotionEvent(upEvent);
    }
}
