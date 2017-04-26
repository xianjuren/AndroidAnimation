package com.animation.animation.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.animation.animation.R;
import com.animation.animation.bean.CustomPropertyName;
import com.animation.animation.utils.DensityUtil;

/**
 * Created by Jone on 17/4/26.
 */
public class CustomPropertyNameActivity extends BaseBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView mTvMove = (TextView) findViewById(R.id.property_name_tv);
        float screenWidth = getResources().getDisplayMetrics().widthPixels;
        float moveX = screenWidth - DensityUtil.dpToPx(this, 80);
        ObjectAnimator animWidth = ObjectAnimator.ofFloat(mTvMove, new CustomPropertyName(Float.class, "width"), 0,
                moveX / 8, moveX / 4, moveX / 2, moveX).setDuration(800);
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(mTvMove, "alpha", 1, 1).setDuration(200);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setInterpolator(new LinearInterpolator());
        mAnimatorSet.playTogether(animWidth, animAlpha);
        mAnimatorSet.start();
    }

    @Override
    int getContentLayoutId() {
        return R.layout.actiivty_property_name;
    }

    @Override
    int getActivityTitle() {
        return R.string.property_name;
    }
}
