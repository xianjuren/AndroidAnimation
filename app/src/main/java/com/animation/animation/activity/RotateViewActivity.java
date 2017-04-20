package com.animation.animation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


import com.animation.animation.R;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RESTART;

/**
 * Created by Jone on 17/4/20.
 */
public class RotateViewActivity extends BaseBackActivity {

    ImageView mCircleBg, mWindmill;
    Animation mCircleBgAnimation, mWindmillScale, mWindmillRotate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleBg = (ImageView) findViewById(R.id.rotating_circle_bg);
        mWindmill = (ImageView) findViewById(R.id.rotating_windmill);
        mCircleBgAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_background_show);
        mCircleBg.setVisibility(View.VISIBLE);
        mCircleBg.startAnimation(mCircleBgAnimation);

        mWindmillScale = AnimationUtils.loadAnimation(this, R.anim.rotate_windmill_scale);
        mWindmillRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_windill_rotate);
        mWindmillRotate.setInterpolator(new LinearInterpolator());
        mCircleBgAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWindmill.setVisibility(View.VISIBLE);
                mWindmill.startAnimation(mWindmillScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mWindmillScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWindmill.startAnimation(mWindmillRotate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void scale(View view) {

    }


    public void overturn(View view) {

    }


    @Override
    int getContentLayoutId() {
        return R.layout.activity_rotating_circle_view;
    }

    @Override
    int getActivityTitle() {
        return R.string.rotating_circle_view;
    }
}
