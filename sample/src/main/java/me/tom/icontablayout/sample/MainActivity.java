package me.tom.icontablayout.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.tom.icontablayout.IconTabLayout;
import me.tom.icontablayout.IconTabLayoutViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IconTabLayout tabLayout = (IconTabLayout) findViewById(R.id.tabLayout);
        IconTabLayoutViewPager viewPager = (IconTabLayoutViewPager) findViewById(R.id.viewPager);
        DemoPagerAdapter adapter = new DemoPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBadgeValue(2, "99+");
    }
}