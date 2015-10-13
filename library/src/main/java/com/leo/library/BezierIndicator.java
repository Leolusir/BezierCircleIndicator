package com.leo.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by littleming on 10/13/15.
 */
public class BezierIndicator extends RelativeLayout{
    private Context context;
    private BezierCircle bezierCircle;
    private LinearLayout tabs;
    private int circleColor = BezierCircle.DEFAULT_COLOR;
    private OnItemSelectedListener onItemSelectedListener;

    public BezierIndicator(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public BezierIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(context, attrs);
        init(context);
    }

    public BezierIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BezierIndicator, 0, 0);
        circleColor = a.getColor(R.styleable.BezierIndicator_bi_circle_color, BezierCircle.DEFAULT_COLOR);
        a.recycle();
    }

    private void init(Context context){
        bezierCircle = new BezierCircle(context);
        bezierCircle.setCircleColor(circleColor);
        tabs = new LinearLayout(context);
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        tabs.setLayoutParams(lp);
        tabs.setBackgroundColor(Color.TRANSPARENT);
        tabs.setPadding(0, 4, 0, 4);
        bezierCircle.setPadding(0, 4, 0, 4);
        bezierCircle.setLayoutParams(lp);
        removeAllViews();
        addView(bezierCircle);
        addView(tabs);
    }

    public void setTabItems(List<TabItem> tabItems){
        tabs.removeAllViews();
        for (int i=0; i<tabItems.size(); i++) {
            final int currentPosition = i;
            TabItem tabItem = tabItems.get(currentPosition);
            View view = tabItem.generateTabView();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            view.setLayoutParams(lp);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemSelectedListener != null) onItemSelectedListener.clickItemSelected(currentPosition);
                }
            });
            view.setLayoutParams(lp);
            tabs.addView(view);
        }
        bezierCircle.setVisibleCount(tabItems.size());
    }

    public void transfer(int position, float offset){
        bezierCircle.transfer(offset, position);
    }

    public void setCircleColor(int color){
        this.circleColor = color;
        bezierCircle.setCircleColor(color);
        bezierCircle.refresh();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener){
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener{
        void clickItemSelected(int position);
    }
}
