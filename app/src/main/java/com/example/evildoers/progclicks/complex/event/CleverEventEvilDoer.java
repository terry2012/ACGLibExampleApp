package com.example.evildoers.progclicks.complex.event;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.acg.EvilApp.R;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

/**
 * Goes through clever/roundabout ways to pass events depending on input
 * TODO test and make sure all of these either get caught or don't work
 */
public class CleverEventEvilDoer extends IndirectClickEvilDoer {

    public enum EventMethod {
        CREATE_FROM_PARCEL,
        OFFSET_LOCATION,
        TRANSFORM,
        ADD_BATCH,
        POST_DELAYED,
        OBTAIN_NO_HISTORY,
        CALL_ON_CLICK,
        ON_KEY_DOWN, // can be used to set pressed
        ON_KEY_UP,
        ON_KEY_MULTIPLE, // to be safe
        ON_GENERIC_MOTION_EVENT,
        ON_TOUCH_EVENT,
        REQUEST_UNBUFFERED_DISPATCH,
        DISPATCH_KEY_EVENT,
        DISPATCH_KEY_EVENT_PRE_IME,
        DISPATCH_KEY_SHORTCUT_EVENT,
        ON_KEY_LONG_PRESS,
    }

    protected int spinnerId;

    public CleverEventEvilDoer(int viewToClickId, int userExposedButtonId, int spinnerId) {
        super(viewToClickId, userExposedButtonId);
        this.spinnerId = spinnerId;
    }

    @Override
    public void doEvil(Activity activity) {
        final View viewToClick = getViewToClick(activity);
        final Spinner spinner = (Spinner) activity.findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.event_methods, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        // Now set the listeners for the disguised malicious button
        final Button button = (Button) activity.findViewById(userExposedButtonId);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EventMethod eventMethod = eventMethodFromSpinner(spinner);
                click(eventMethod, viewToClick, button, event);
                return true;
            }
        });
    }

    @TargetApi(23)
    protected void click(EventMethod eventMethod, final View viewToClick, Button button, final MotionEvent event) {
        switch (eventMethod) {
            case CREATE_FROM_PARCEL:
                // TODO: Is this possible?
                break;
            case OFFSET_LOCATION:
                event.offsetLocation(viewToClick.getX() - event.getX(), viewToClick.getY() - event.getY());
                viewToClick.dispatchTouchEvent(event);
                break;
            case TRANSFORM:
                Matrix matrix = new Matrix();
                matrix.set(button.getMatrix());
                matrix.setTranslate(viewToClick.getX() - event.getX(), viewToClick.getY() - event.getY());
                event.transform(matrix);
                viewToClick.dispatchTouchEvent(event);
                break;
            case ADD_BATCH:
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                pointerCoords.setAxisValue(MotionEvent.AXIS_X, viewToClick.getX());
                pointerCoords.setAxisValue(MotionEvent.AXIS_Y, viewToClick.getY());
                event.addBatch(SystemClock.uptimeMillis(), new MotionEvent.PointerCoords[]{pointerCoords}, 0);
                viewToClick.dispatchTouchEvent(event);
                break;
            case POST_DELAYED:
                viewToClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewToClick.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewToClick.dispatchTouchEvent(event);
                            }
                        }, 5000);
                    }
                });
                break;
            case OBTAIN_NO_HISTORY:
                viewToClick.dispatchTouchEvent(MotionEvent.obtainNoHistory(event));
                break;
            case ON_KEY_DOWN:
                viewToClick.onKeyDown(KeyEvent.KEYCODE_ENTER, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case ON_KEY_UP:
                viewToClick.onKeyUp(KeyEvent.KEYCODE_ENTER, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                break;
            case ON_KEY_MULTIPLE:
                viewToClick.onKeyMultiple(KeyEvent.KEYCODE_ENTER, 1, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                break;
            case ON_GENERIC_MOTION_EVENT:
                viewToClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewToClick.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewToClick.onGenericMotionEvent(event);
                            }
                        }, 5000);
                    }
                });
                break;
            case ON_TOUCH_EVENT:
                viewToClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewToClick.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewToClick.onTouchEvent(event);
                            }
                        }, 5000);
                    }
                });
                break;
            case REQUEST_UNBUFFERED_DISPATCH:
                viewToClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewToClick.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewToClick.requestUnbufferedDispatch(event);
                            }
                        }, 5000);
                    }
                });
                break;
            case DISPATCH_KEY_EVENT:
                viewToClick.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case DISPATCH_KEY_EVENT_PRE_IME:
                viewToClick.dispatchKeyEventPreIme(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case DISPATCH_KEY_SHORTCUT_EVENT:
                viewToClick.dispatchKeyShortcutEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case ON_KEY_LONG_PRESS:
                viewToClick.onKeyLongPress(KeyEvent.KEYCODE_ENTER, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
        }
    }

    protected EventMethod eventMethodFromSpinner(Spinner spinner) {
        return Enum.valueOf(EventMethod.class, spinner.getSelectedItem().toString());
    }
}