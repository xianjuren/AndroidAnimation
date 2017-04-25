package com.animation.animation.bean;

import android.view.View;

/**
 * Created by Jone on 17/4/25.
 */

public class LineWidth {

    private View mView;

    public LineWidth(View view) {
        mView = view;
    }

    public float getLineWidth() {
        return mView.getLayoutParams().width;
    }

    public void setLineWidth(int width) {
        mView.getLayoutParams().width = width;
        mView.requestLayout();
    }
}
