package com.leo.beziercircleindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.leo.beziercircleindicator.pager.ViewPagerAdapter;
import com.leo.library.BezierIndicator;
import com.leo.library.TabItem;

import java.util.ArrayList;
import java.util.List;

public class IndicatorActivity extends AppCompatActivity {
    private BezierIndicator indicator;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start here
        indicator = (BezierIndicator)findViewById(R.id.bezier_indicator);
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        int[] resIds = {R.drawable.poker_face, R.drawable.joker_face, R.drawable.faker_face, R.drawable.arker_face};

        List<TabItem> tabItems = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TabItem tabItem = new TabItem(this);
            tabItem.setTitle("Tab -" + i);
            tabItem.setTitleSize(15);
            tabItem.setTitleColor(Color.WHITE);
            tabItems.add(tabItem);
        }
        indicator.setTabItems(tabItems);

        indicator.setOnItemSelectedListener(new BezierIndicator.OnItemSelectedListener() {
            @Override
            public void clickItemSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        indicator.invalidate();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), tabItems.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.transfer(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
