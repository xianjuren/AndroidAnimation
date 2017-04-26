package com.animation.animation.activity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.animation.animation.R;
import com.animation.animation.utils.DensityUtil;

/**
 * Created by Jone on 17/4/26.
 */
public class KeyframeActivity extends BaseBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = (ImageView) findViewById(R.id.key_frame_iv);
        float moveX = getResources().getDisplayMetrics().widthPixels - DensityUtil.dpToPx(this, 40);
        Keyframe[] keyframes = new Keyframe[]{Keyframe.ofFloat(0, 0),
                Keyframe.ofFloat(0.25f, moveX / 2),
                Keyframe.ofFloat(0.5f, 0),
                Keyframe.ofFloat(0.75f, moveX / 3),
                Keyframe.ofFloat(1f, moveX)};

        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
        ObjectAnimator.ofPropertyValuesHolder(imageView,valuesHolder).setDuration(2000).start();
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_key_frame;
    }

    @Override
    int getActivityTitle() {
        return R.string.key_frame;
    }
}
