package com.animation.animation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.animation.animation.R;

/**
 * Created by Jone on 17/4/18.
 */
public abstract class BaseBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_base_back);
        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.base_activity_content_layout);
        getLayoutInflater().inflate(getContentLayoutId(), mFrameLayout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.base_activity_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getActivityTitle());
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //不必添加的方法
        //左上角的图标是否可以点击
        getSupportActionBar().setHomeButtonEnabled(true);
        //使左上角图标是否显示
        //Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//// 使自定义的普通View能在title栏显示
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    abstract int getContentLayoutId();

    abstract int getActivityTitle();


}
