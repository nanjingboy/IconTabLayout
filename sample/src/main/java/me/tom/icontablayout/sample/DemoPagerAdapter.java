package me.tom.icontablayout.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import me.tom.icontablayout.AbstractIconTabLayoutViewPagerAdapter;
import me.tom.icontablayout.sample.fragment.MeFragment;
import me.tom.icontablayout.sample.fragment.MessageFragment;
import me.tom.icontablayout.sample.fragment.MoneyFragment;
import me.tom.icontablayout.sample.fragment.PeopleFragment;

public class DemoPagerAdapter extends AbstractIconTabLayoutViewPagerAdapter {

    public DemoPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoneyFragment();
            case 1:
                return new PeopleFragment();
            case 2:
                return new MessageFragment();
            case 3:
                return new MeFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Money";
            case 1:
                return "People";
            case 2:
                return "Message";
            case 3:
                return "Me";
            default:
                return null;
        }
    }

    @Override
    public int getPageIconResId(int position) {
        switch (position) {
            case 0:
                return R.mipmap.money;
            case 1:
                return R.mipmap.people;
            case 2:
                return R.mipmap.message;
            case 3:
                return R.mipmap.me;
            default:
                return 0;
        }
    }

    @Override
    public int getPageSelectedIconResId(int position) {
        switch (position) {
            case 0:
                return R.mipmap.money_selected;
            case 1:
                return R.mipmap.people_selected;
            case 2:
                return R.mipmap.message_selected;
            case 3:
                return R.mipmap.me_selected;
            default:
                return 0;
        }
    }
}
