package com.example.evildoers.progclicks.complex;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.view.HapticFeedbackConstants;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.acg.EvilApp.R;
import com.example.evildoers.progclicks.simple.IndirectClickEvilDoer;

/**
 * Goes through clever/roundabout ways to click depending on input
 */
public class CleverClickEvilDoer extends IndirectClickEvilDoer {

    public enum ClickMethod {
        SET_PRESSED,
        CALL_ON_CLICK,
        PERFORM_LONG_CLICK,
        CANCEL_LONG_PRESS, // may be impossible to use interestingly, trying anyways
        SET_TOUCH_DELEGATE, // can set larger TD for parent (or another button), looks innocent to us
        DISPATCH_UNHANDLED_MOVE, // not passed down through our wrapper
        DISPATCH_NESTED_PRE_SCROLL, // not passed down through our wrapper
        DISPATCH_NESTED_SCROLL, // not passed down through our wrapper
        DISPATCH_NESTED_FLING, // not passed down through our wrapper
        DISPATCH_NESTED_PRE_FLING, // not passed down through our wrapper
        DISPATCH_DRAG_EVENT, // not passed down through our wrapper
        ON_CREATE_INPUT_CONNECTION, // not passed down through our wrapper
        ON_DRAG_EVENT, // not passed down through our wrapper
        SET_HOVERED, // not passed down through our wrapper
        ON_HOVER_CHANGED, // not passed down through our wrapper
        PERFORM_CONTEXT_CLICK, // not passed down through our wrapper
        PERFORM_HAPTIC_FEEDBACK // not passed down through our wrapper
    };

    protected int spinnerId;

    public CleverClickEvilDoer(int viewToClickId, int userExposedButtonId, int spinnerId) {
        super(viewToClickId, userExposedButtonId);
        this.spinnerId = spinnerId;
    }

    @Override
    public void doEvil(Activity activity) {
        final View viewToClick = getViewToClick(activity);
        final Spinner spinner = (Spinner) activity.findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.click_methods, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        // Now set the listeners for the disguised malicious button
        final Button button = (Button) activity.findViewById(userExposedButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMethod clickMethod = clickMethodFromSpinner(spinner);
                click(clickMethod, viewToClick, button);
            }
        });
    }

    @TargetApi(23)
    protected void click(ClickMethod clickMethod, final View viewToClick, Button button) {
        switch (clickMethod) {
            case SET_PRESSED:
                viewToClick.setPressed(true);
                break;
            case CALL_ON_CLICK:
                viewToClick.callOnClick();
                break;
            case PERFORM_LONG_CLICK:
                viewToClick.performLongClick();
                break;
            case CANCEL_LONG_PRESS:
                viewToClick.cancelLongPress();
                break;
            case SET_TOUCH_DELEGATE:
                viewToClick.setTouchDelegate(new TouchDelegate(new Rect(button.getLeft(), button.getTop(), button.getRight(), button.getBottom()), viewToClick));
                break;
            case DISPATCH_UNHANDLED_MOVE:
                viewToClick.dispatchUnhandledMove(viewToClick, View.FOCUS_DOWN);
                break;
            case DISPATCH_NESTED_PRE_SCROLL:
                viewToClick.dispatchNestedPreScroll(0, 0, new int[0], new int[0]);
                break;
            case DISPATCH_NESTED_SCROLL:
                viewToClick.dispatchNestedScroll(0, 0, 0, 0, new int[0]);
                break;
            case DISPATCH_NESTED_FLING:
                viewToClick.dispatchNestedFling(0, 0, true);
                break;
            case DISPATCH_NESTED_PRE_FLING:
                viewToClick.dispatchNestedPreFling(0, 0);
                break;
            case DISPATCH_DRAG_EVENT:
                //viewToClick.dispatchDragEvent(DragEvent.ACTION_DRAG_STARTED.); TODO
                break;
            case ON_CREATE_INPUT_CONNECTION:
                viewToClick.onCreateInputConnection(new EditorInfo());
                break;
            case ON_DRAG_EVENT:
                //viewToClick.onDragEvent() TODO
                break;
            case SET_HOVERED:
                viewToClick.setHovered(true);
                break;
            case ON_HOVER_CHANGED:
                viewToClick.onHoverChanged(true);
                break;
            case PERFORM_CONTEXT_CLICK:
                viewToClick.performContextClick();
                break;
            case PERFORM_HAPTIC_FEEDBACK:
                viewToClick.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                break;
        }
    }

    protected ClickMethod clickMethodFromSpinner(Spinner spinner) {
        return Enum.valueOf(ClickMethod.class, spinner.getSelectedItem().toString());
    }
}