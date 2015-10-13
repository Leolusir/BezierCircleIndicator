package com.leo.library;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by littleming on 10/13/15.
 */
public class TabItem{
    private View tabView;
    private TextView tvTitle;
    private ImageView image;

    public TabItem(Context context){
        init(context);
    }

    private void init(Context context){
        tabView = View.inflate(context, R.layout.tab_item, null);
        tvTitle = (TextView)tabView.findViewById(R.id.tab_item_title);
        image = (ImageView)tabView.findViewById(R.id.tab_item_img);
        tvTitle.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
    }

    public View generateTabView(){
        return tabView;
    }

    public void setTitle(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    public void setTitleColor(int color){
        tvTitle.setTextColor(color);
    }

    public void setTitleSize(int size){
        tvTitle.setTextSize(size);
    }

    public void setImageByResId(int resId){
        image.setVisibility(View.VISIBLE);
        image.setImageResource(resId);
    }
}
