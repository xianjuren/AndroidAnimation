package com.animation.animation.application;

import android.content.Context;

/**
 * Created by Jone on 17/4/21.
 */
public class GlobalConfig {

    public static Context mContext;
    public static void init(Context context) {
        mContext =context;
    }

    public static Context getContext() {
        return mContext;
    }
}
