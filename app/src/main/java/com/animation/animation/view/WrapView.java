package com.animation.animation.view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Jone on 17/4/26.
 */
public class WrapView {

    private TextView view;
    private int width;

    public WrapView(TextView view) {
        this.view = view;
    }


    public void setMoveWidth(int width) {
        this.width = width;
        Log.i("====结果==", width + "");
        this.view.getLayoutParams().width = width;
        this.view.requestLayout();
    }

}
