package com.animation.animation.activity;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.animation.animation.R;
import com.animation.animation.utils.DensityUtil;

/**
 * Created by Jone on 17/4/26.
 */
public class LayoutTransitionActivity extends BaseBackActivity {

    LayoutTransition mTransitioner;
    LinearLayout mllIcons;
    int mScreenWidth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mllIcons = (LinearLayout) findViewById(R.id.layout_transition_ll);
        resetLayoutTransition();
        intoAnimation();
        initIcons();
    }

    private void intoAnimation() {
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        Object object = null;
        ObjectAnimator animatorInto = ObjectAnimator.ofFloat(object, "translationX", -mScreenWidth / 2, 0).setDuration(500);
        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(object, "translationX", 0, -mScreenWidth / 2).setDuration(500);
        ObjectAnimator animatorShow = ObjectAnimator.ofFloat(object, "alpha", 0, 1).setDuration(500);
        ObjectAnimator animatorHide = ObjectAnimator.ofFloat(object, "alpha", 1, 0).setDuration(500);
       // mTransitioner.setStagger(LayoutTransition.DISAPPEARING, 100);
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animatorInto);
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animatorOut);

    }

    private void initIcons() {

        for (int i = 0; i < 4; i++) {
            addView();
        }
    }

    private void addView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = (int) DensityUtil.dpToPx(this, 40);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.height = (int) DensityUtil.dpToPx(this, 40);
        params.setMargins(10, 10, 10, 10);
        imageView.setLayoutParams(params);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mllIcons.removeView(v);
            }
        });
        mllIcons.addView(imageView);
    }

    public void addView(View view) {
        addView();
    }

    public void removeView(View view) {
        mllIcons.removeViewAt(0);
    }

    private void resetLayoutTransition() {
        mTransitioner = new LayoutTransition();
        mllIcons.setLayoutTransition(mTransitioner);
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_layout_transition;
    }

    @Override
    int getActivityTitle() {
        return R.string.property_layout_animation;
    }
}
