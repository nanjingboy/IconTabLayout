package me.tom.icontablayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class AbstractIconTabLayoutViewPagerAdapter extends FragmentPagerAdapter {

    public AbstractIconTabLayoutViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public abstract int getPageIconResId(int position);
    public abstract int getPageSelectedIconResId(int position);
}
