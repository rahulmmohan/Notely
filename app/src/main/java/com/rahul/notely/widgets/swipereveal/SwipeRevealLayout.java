package com.rahul.notely.widgets.swipereveal;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;



/**
 * Copied
 * Simplification of https://github.com/chthai64/SwipeRevealLayout which fixes some recyclerview layout issues
 */
public class SwipeRevealLayout extends FrameLayout {
    private static final int FLING_THRESHOLD = 50;

    public interface OnOpenChangedListener {
        void onOpenChanged(boolean isOpen);
    }

    private View mMainView;
    private View mRevealView;

    private ViewDragHelper mDragHelper;
    private GestureDetectorCompat mGestureDetector;

    private boolean mIsScrolling = false;
    private boolean mIsOpen = false;
    private OnOpenChangedListener mExternalOpenChangeListener;
    private OnOpenChangedListener mInternalOpenChangeListener;

    public SwipeRevealLayout(Context context) {
        super(context);
        init();
    }

    public SwipeRevealLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeRevealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, mDragCallback);
        mGestureDetector = new GestureDetectorCompat(getContext(), new SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                mIsScrolling = false;
                return super.onDown(e);
            }

            @Override
            public boolean onContextClick(MotionEvent e) {
                return super.onContextClick(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mIsScrolling = true;

                if(getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    public void setOpenChangedListener(OnOpenChangedListener listener) {
        mExternalOpenChangeListener = listener;
    }

    void setInternalOpenChangedListener(OnOpenChangedListener listener) {
        mInternalOpenChangeListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        mGestureDetector.onTouchEvent(ev);
        return mDragHelper.getViewDragState() == ViewDragHelper.STATE_SETTLING ||
                mDragHelper.getViewDragState() == ViewDragHelper.STATE_IDLE && mIsScrolling;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalArgumentException("This view must contain exactly 2 views");
        }

        mRevealView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int xPos = getMainViewLeft(left);
        mMainView.layout(xPos,
                top,
                xPos + mMainView.getMeasuredWidth(),
                bottom);
        mRevealView.layout(xPos + mMainView.getMeasuredWidth(),
                top,
                xPos + mMainView.getMeasuredWidth() + mRevealView.getMeasuredWidth(),
                bottom);
    }

    public void setOpen(boolean isOpen, boolean animate) {
        if (isOpen != mIsOpen) {
            if (mExternalOpenChangeListener != null) {
                mExternalOpenChangeListener.onOpenChanged(isOpen);
            }
            if (mInternalOpenChangeListener != null) {
                mInternalOpenChangeListener.onOpenChanged(isOpen);
            }
        }
        mIsOpen = isOpen;

        if (animate) {
            mDragHelper.smoothSlideViewTo(mMainView, getMainViewLeft(getLeft()), getTop());
        } else {
            requestLayout();
        }
        ViewCompat.postInvalidateOnAnimation(SwipeRevealLayout.this);
    }

    private int getMainViewLeft(int thisLeft) {
        return mIsOpen ? thisLeft - mRevealView.getMeasuredWidth() : thisLeft;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private final ViewDragHelper.Callback mDragCallback = new Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (isEnabled()) {
                mDragHelper.captureChildView(mMainView, pointerId);
            }
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return Math.max(Math.min(left, getLeft()), getLeft() - mRevealView.getMeasuredWidth());
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mRevealView.offsetLeftAndRight(dx);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            float scaledVelocity = xvel / getContext().getResources().getDisplayMetrics().density;
            if (scaledVelocity <= -FLING_THRESHOLD) {
                setOpen(true, true);
            } else if (scaledVelocity >= FLING_THRESHOLD) {
                setOpen(false, true);
            } else if (releasedChild.getLeft() < getLeft() - mRevealView.getMeasuredWidth() / 2) {
                setOpen(true, true);
            } else {
                setOpen(false, true);
            }
        }
    };
}
