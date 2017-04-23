package com.animation.animation.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.animation.animation.R;
import com.animation.animation.animation.ListenerAnimationDrawable;

/**
 * Created by Jone on 17/4/23.
 */
public class FrameAnimationActivity extends BaseBackActivity {

    ImageView mImageView;
    AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView = (ImageView) findViewById(R.id.activity_frame_iv);
        //无监听写法
//        mImageView.setBackgroundResource(R.drawable.frame_side_to_right);
//        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
//        mAnimationDrawable.start();
        
        //监听帧动画，可以通过计算执行动画的时间来处触发逻辑
        ListenerAnimationDrawable listenerAnimationDrawable = new ListenerAnimationDrawable((AnimationDrawable) getResources().getDrawable(R.drawable.frame_side_to_right)) {
            @Override
            public void endAnimation() {
                mImageView.setVisibility(View.GONE);
            }
        };
        listenerAnimationDrawable.setOneShot(true);
        mImageView.setBackgroundDrawable(listenerAnimationDrawable);
        listenerAnimationDrawable.start();


    }


    @Override
    int getContentLayoutId() {
        return R.layout.activity_frame;
    }

    @Override
    int getActivityTitle() {
        return R.string.frame_animation;
    }


}
