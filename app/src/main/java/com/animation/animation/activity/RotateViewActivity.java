package com.animation.animation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.animation.animation.R;
import com.animation.animation.animation.Rotate3dAnimation;


/**
 * Created by Jone on 17/4/20.
 */
public class RotateViewActivity extends BaseBackActivity {

    ImageView mCircleBg, mWindmill;
    Animation mCircleBgAnimation, mWindmillScale, mWindmillRotate, mWindmillHide, mAdAnimation;
    LinearLayout mAdLayout;
    Rotate3dAnimation mRotate3dAnimation;
    RelativeLayout mWindmillLayout;
    boolean isOverturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleBg = (ImageView) findViewById(R.id.rotating_circle_bg);
        mWindmill = (ImageView) findViewById(R.id.rotating_windmill);
        mWindmillLayout = (RelativeLayout) findViewById(R.id.windmill_layout);
        mAdLayout = (LinearLayout) findViewById(R.id.ad_item);
        mCircleBgAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_background_show);
        mWindmillLayout.setVisibility(View.VISIBLE);
        mCircleBg.setVisibility(View.VISIBLE);
        mCircleBg.startAnimation(mCircleBgAnimation);
        mWindmillScale = AnimationUtils.loadAnimation(this, R.anim.rotate_windmill_scale);
        mWindmillRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_windill_rotate);
        mWindmillRotate.setInterpolator(new LinearInterpolator());
        mWindmillHide = AnimationUtils.loadAnimation(this, R.anim.windmill_hide);
        mAdAnimation = AnimationUtils.loadAnimation(this, R.anim.ad_show);
        mAdAnimation.setInterpolator(new BounceInterpolator());

        mRotate3dAnimation = new Rotate3dAnimation(Rotate3dAnimation.Rotate_X, 90, 0, mAdLayout.getWidth() / 2, mAdLayout.getHeight() / 2, 0, true);
        mRotate3dAnimation.setFillAfter(true);
        mRotate3dAnimation.setDuration(800);
        mRotate3dAnimation.setInterpolator(new LinearInterpolator());
        setListener();

    }

    private void setListener() {
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

        mWindmillHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWindmillLayout.setVisibility(View.GONE);
                if (isOverturn) {
                    mAdLayout.startAnimation(mRotate3dAnimation);
                } else {
                    mAdLayout.startAnimation(mAdAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAdAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mAdLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAdLayout.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }


    public void scale(View view) {
        isOverturn = false;
        mWindmill.clearAnimation();
        mAdLayout.clearAnimation();
        mAdLayout.setVisibility(View.INVISIBLE);
        mWindmillLayout.setVisibility(View.VISIBLE);
        mWindmillLayout.startAnimation(mWindmillHide);
    }


    public void overturn(View view) {
        isOverturn = true;
        mWindmill.clearAnimation();
        mAdLayout.clearAnimation();
        mAdLayout.setVisibility(View.INVISIBLE);
        mWindmillLayout.setVisibility(View.VISIBLE);
        mWindmillLayout.startAnimation(mWindmillHide);
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
