package com.rahul.notely.widgets.swipereveal;

import android.support.v4.util.LongSparseArray;




public class RevealAdapterHelper {
    private final LongSparseArray<SwipeRevealLayout> mLayoutCache = new LongSparseArray<>();
    private final LongSparseArray<Boolean> mOpenCache = new LongSparseArray<>();

    private OpenMode mOpenMode = OpenMode.MULTIPLE;

    public enum OpenMode {
        SINGLE,
        MULTIPLE
    }

    public void setOpenMode(OpenMode mode) {
        mOpenMode = mode;
    }

    public void bind(final long id, SwipeRevealLayout layout) {
        for (int i = 0; i < mLayoutCache.size(); i++) {
            if (mLayoutCache.valueAt(i) == layout) {
                mLayoutCache.removeAt(i);
            }
        }
        mLayoutCache.put(id, layout);

        layout.setInternalOpenChangedListener(new SwipeRevealLayout.OnOpenChangedListener() {
            @Override
            public void onOpenChanged(boolean isOpen) {
                mOpenCache.put(id, isOpen);

                if (isOpen && mOpenMode == OpenMode.SINGLE) {
                    closeOthers(id);
                }
            }
        });

        if (mOpenCache.get(id, false)) {
            layout.setOpen(true, false);
        } else {
            layout.setOpen(false, false);
        }
    }

    private void closeOthers(long openId) {
        for (int i = 0; i < mLayoutCache.size(); i++) {
            if (mLayoutCache.keyAt(i) != openId) {
                mLayoutCache.valueAt(i).setOpen(false, true);
            }
        }
        for (int i = 0; i < mOpenCache.size(); i++) {
            if (mOpenCache.keyAt(i) != openId) {
                mOpenCache.setValueAt(i, false);
            }
        }
    }
}
