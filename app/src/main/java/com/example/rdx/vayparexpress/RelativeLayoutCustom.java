package com.example.rdx.vayparexpress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by stpl on 28-12-2017.
 */

public class RelativeLayoutCustom extends FrameLayout {
    public RelativeLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* public RelativeLayoutCustom(Context context) {
            super(context);
        }*/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Do whatever with your touch event
        return false; // Do not prevent the rest of the layout from being touched
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent e) {
//        // do what you need to with the event, and then...
//        return super.dispatchTouchEvent(e);
//    }
}
