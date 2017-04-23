package com.animation.animation.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

/**
 * Created by Jone on 17/4/23.
 */

public abstract class ListenerAnimationDrawable extends AnimationDrawable {



    public ListenerAnimationDrawable(AnimationDrawable drawable) {
        for (int i = 0; i < drawable.getNumberOfFrames(); i++) {
            addFrame(drawable.getFrame(i), drawable.getDuration(i));
        }
    }



    @Override
    public void start() {
        super.start();
        long totalAnimationTime = getTotalTime();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                endAnimation();
            }
        }, totalAnimationTime);

    }

    abstract public void endAnimation();

    private long getTotalTime() {
        int durationTime = 0;
        for (int i = 0; i < getNumberOfFrames(); i++) {
            durationTime += getDuration(i);
        }
        return durationTime;
    }



}
