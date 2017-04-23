package com.animation.animation.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.animation.animation.R;
import com.animation.animation.view.AutoTextView;

/**
 * Created by Jone on 17/4/23.
 */
public class Rotate3dViewActivity extends BaseBackActivity implements View.OnClickListener {

    RelativeLayout mPicture;
    LinearLayout mText;
    Button mBtnNext, mBtnPrev;
    AutoTextView mAutoTextView;
    private static int sCount = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPicture = (RelativeLayout) findViewById(R.id.picture_3d);
        mText = (LinearLayout) findViewById(R.id.text_3d);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPrev = (Button) findViewById(R.id.prev);
        mAutoTextView = (AutoTextView) findViewById(R.id.switcher02);
        mAutoTextView.setText("Hello world!");
        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
    }

    public void picture(View view) {
        mPicture.setVisibility(View.VISIBLE);
        mText.setVisibility(View.GONE);
    }


    public void text(View view) {
        mText.setVisibility(View.VISIBLE);
        mPicture.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.next:
                mAutoTextView.next();
                sCount++;
                break;
            case R.id.prev:
                mAutoTextView.previous();
                sCount--;
                break;
        }
        mAutoTextView.setText(sCount%2==0 ?
                sCount+"AAFirstAA" :
                sCount+"BBBBBBB");

    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_rotate3d;
    }

    @Override
    int getActivityTitle() {
        return R.string.rotating_3d;
    }
}
