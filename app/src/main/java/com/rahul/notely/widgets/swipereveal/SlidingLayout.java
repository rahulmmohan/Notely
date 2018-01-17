package com.rahul.notely.widgets.swipereveal;

/**
 * Created by Rahul on 17/01/18.
 */

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * copied from http://v4all123.blogspot.in/2013/11/simple-sliding-menu-example-in-android.html ## made changes for sliding from right
 */
public class SlidingLayout extends LinearLayout {

    private static final int SLIDING_DURATION = 500;
    private static final int QUERY_INTERVAL = 16;
    private ViewGroup slidingView;
    private View filter;
    private View content;

    private enum MenuState {
        HIDING, HIDDEN, SHOWING, SHOWN,
    };

    private int contentXOffset;
    private MenuState currentMenuState = MenuState.HIDDEN;
    private Scroller menuScroller = new Scroller(this.getContext(),
            new EaseInInterpolator());
    private Runnable menuRunnable = new MenuRunnable();
    private Handler menuHandler = new Handler();


    public SlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingLayout(Context context) {
        super(context);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        slidingView = (ViewGroup) this.getChildAt(0);
        content = slidingView.getChildAt(0);
        filter = slidingView.getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout( changed,  left,  top,  right,
         bottom);
        slidingView.layout(left , top, right+filter.getWidth() ,
                bottom);

    }

    public void toggleMenu() {

        if (currentMenuState == MenuState.HIDING
                || currentMenuState == MenuState.SHOWING)
            return;

        switch (currentMenuState) {
            case HIDDEN:
                currentMenuState = MenuState.SHOWING;
                menuScroller.startScroll(0, 0, filter.getWidth(), 0,
                        SLIDING_DURATION);
                break;
            case SHOWN:
                currentMenuState = MenuState.HIDING;
                menuScroller.startScroll(contentXOffset, 0, -contentXOffset, 0,
                        SLIDING_DURATION);
                break;
            default:
                break;
        }
        menuHandler.postDelayed(menuRunnable, QUERY_INTERVAL);
        this.invalidate();
    }

    protected class MenuRunnable implements Runnable {
        @Override
        public void run() {
            boolean isScrolling = menuScroller.computeScrollOffset();
            adjustContentPosition(isScrolling);
        }
    }

    private void adjustContentPosition(boolean isScrolling) {
        int scrollerXOffset = menuScroller.getCurrX();

        slidingView.offsetLeftAndRight(contentXOffset-scrollerXOffset );

        contentXOffset = scrollerXOffset;
        this.invalidate();
        if (isScrolling)
            menuHandler.postDelayed(menuRunnable, QUERY_INTERVAL);
        else
            this.onMenuSlidingComplete();
    }

    private void onMenuSlidingComplete() {
        switch (currentMenuState) {
            case SHOWING:
                currentMenuState = MenuState.SHOWN;
                break;
            case HIDING:
                content.setEnabled(true);
                currentMenuState = MenuState.HIDDEN;
                break;
            default:
                return;
        }
    }

    protected class EaseInInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float t) {
            return (float) Math.pow(t - 1, 5) + 1;
        }

    }

    public boolean isMenuShown() {
        return currentMenuState == MenuState.SHOWN;
    }

}
