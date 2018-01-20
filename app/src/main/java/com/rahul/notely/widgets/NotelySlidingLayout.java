package com.rahul.notely.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jkb.slidemenu.SlideMenuLayout;

/**
 * Created by Rahul on 20/01/18.
 */

public class NotelySlidingLayout extends SlideMenuLayout {
    public NotelySlidingLayout(Context context) {
        super(context);
    }

    public NotelySlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotelySlidingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //Remove touch functionality to allow list iem to detect touch events
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;

    }


}
