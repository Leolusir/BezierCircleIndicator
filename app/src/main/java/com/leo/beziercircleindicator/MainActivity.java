package com.leo.beziercircleindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.library.BezierCircle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private BezierCircle bezierCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "leo.lusir@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //start here
        bezierCircle = (BezierCircle)findViewById(R.id.bezier);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 4));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bezierCircle.transfer(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        findViewById(R.id.text_view_1).setOnClickListener(this);
        findViewById(R.id.text_view_2).setOnClickListener(this);
        findViewById(R.id.text_view_3).setOnClickListener(this);
        findViewById(R.id.text_view_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.text_view_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.text_view_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.text_view_4:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private int count;

        public ViewPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Fragment getItem(int i) {
            return new PagerItem();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PagerItem pagerItem = (PagerItem)super.instantiateItem(container, position);

            return pagerItem;
        }
    }

    class PagerItem extends Fragment {
        private View rootView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if(rootView == null){
                rootView = inflater.inflate(R.layout.pager_item, container, false);
            }else{
                ViewGroup parent = (ViewGroup)rootView.getParent();
                if(parent != null){
                    parent.removeView(rootView);
                }
            }

            return rootView;
        }

    }
}
