package me.tom.icontablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class IconTabLayout extends TabLayout {

    private int mTabTextColor;
    private int mTabSelectedTextColor;

    private LayoutInflater mInflater;
    private AbstractIconTabLayoutViewPagerAdapter mAdapter;

    public IconTabLayout(Context context) {
        this(context, null);
    }

    public IconTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, android.support.design.R.styleable.TabLayout);
        mTabTextColor = typedArray.getColor(
                android.support.design.R.styleable.TabLayout_tabTextColor,
                Color.LTGRAY
        );
        mTabSelectedTextColor = typedArray.getColor(
                android.support.design.R.styleable.TabLayout_tabSelectedTextColor,
                Color.LTGRAY
        );
        typedArray.recycle();
        setTabMode(MODE_FIXED);
        setSelectedTabIndicatorColor(Color.TRANSPARENT);
        setSelectedTabIndicatorHeight(0);
        addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                refreshTab(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(Tab tab) {
                refreshTab(tab.getPosition(), false);
            }

            @Override
            public void onTabReselected(Tab tab) {
            }
        });
    }

    public void setBadgeValue(int tabPosition, String badgeValue) {
        View view = getTabAt(tabPosition).getCustomView();
        if (view == null) {
            return;
        }
        TextView badgeView = (TextView) view.findViewById(R.id.badgeView);
        badgeView.setText(badgeValue);
        if (badgeValue == null || badgeValue.length() == 0) {
            badgeView.setVisibility(INVISIBLE);
        } else {
            badgeView.setVisibility(VISIBLE);
        }
    }

    public void setTabTextColor(int color) {
        mTabTextColor = color;
        int count = getTabCount();
        int selectedPosition = getSelectedTabPosition();
        for (int index = 0; index < count; index++) {
            if (index == selectedPosition) {
                continue;
            }
            refreshTab(index, false);
        }
    }

    public void setTabSelectedTextColor(int color) {
        mTabSelectedTextColor = color;
        refreshTab(getSelectedTabPosition(), true);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
        super.setupWithViewPager(viewPager, autoRefresh);
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter instanceof AbstractIconTabLayoutViewPagerAdapter) {
                mAdapter = (AbstractIconTabLayoutViewPagerAdapter) adapter;
            } else {
                mAdapter = null;
            }
            if (mAdapter == null) {
                return;
            }
            int count = getTabCount();
            for (int index = 0; index < count; index++) {
                View view = mInflater.inflate(R.layout.icon_tab_layout_item, null);
                ((ImageView) view.findViewById(R.id.iconView)).setImageResource(
                        mAdapter.getPageIconResId(index)
                );
                TextView titleView = (TextView) view.findViewById(R.id.titleView);
                titleView.setText(mAdapter.getPageTitle(index));
                titleView.setTextColor(mTabTextColor);
                getTabAt(index).setCustomView(view);
            }
            refreshTab(getSelectedTabPosition(), true);
        }
    }

    protected void refreshTab(int position, boolean isSelected) {
        if (mAdapter == null) {
            return;
        }
        View view = getTabAt(position).getCustomView();
        if (view != null) {
            if (isSelected) {
                ((ImageView) view.findViewById(R.id.iconView)).setImageResource(
                        mAdapter.getPageSelectedIconResId(position)
                );
                ((TextView) view.findViewById(R.id.titleView)).setTextColor(mTabSelectedTextColor);
            } else {
                ((ImageView) view.findViewById(R.id.iconView)).setImageResource(
                        mAdapter.getPageIconResId(position)
                );
                ((TextView) view.findViewById(R.id.titleView)).setTextColor(mTabTextColor);
            }
        }
    }
}
