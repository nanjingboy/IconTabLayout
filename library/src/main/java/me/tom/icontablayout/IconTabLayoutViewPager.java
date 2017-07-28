package me.tom.icontablayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class IconTabLayoutViewPager extends ViewPager {

    private boolean mIsPagingEnabled;

    public IconTabLayoutViewPager(Context context) {
        this(context, null);
    }

    public IconTabLayoutViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPagingEnabled(boolean isPagingEnabled) {
        mIsPagingEnabled = isPagingEnabled;
    }

    @Override
    public void setCurrentItem(int item) {
        if (mIsPagingEnabled) {
            super.setCurrentItem(item);
        } else {
            setCurrentItem(item, false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIsPagingEnabled) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mIsPagingEnabled) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}
