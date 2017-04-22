package com.animation.animation.application;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Jone on 17/4/21.
 */

public class AnimationApplication  extends Application{

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalConfig.init(AnimationApplication.this);
    }
}
