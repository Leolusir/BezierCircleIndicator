package com.leo.beziercircleindicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        findViewById(R.id.text_indicator).setOnClickListener(this);
        findViewById(R.id.image_indicator).setOnClickListener(this);
        findViewById(R.id.text_with_image_indicator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_indicator:
                startActivity(new Intent(MainActivity.this, IndicatorActivity.class));
                break;
            case R.id.image_indicator:
                startActivity(new Intent(MainActivity.this, ImageIndicatorActivity.class));
                break;
            case R.id.text_with_image_indicator:
                startActivity(new Intent(MainActivity.this, TextWithImageActivity.class));
                break;
        }
    }
}
