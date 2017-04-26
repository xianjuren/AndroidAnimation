package com.animation.animation.view.typeEvalueator;

import android.animation.TypeEvaluator;


import com.animation.animation.bean.CirclePoint;

/**
 * Created by Jone on 17/4/26.
 * 1,固定时间抛物线，水平方向200px/s，垂直方向加速度200px/s*s
 * 那么公式就是：x = 200t ; y=200t²。
 * 2.固定长度的抛物线，小球滚动到屏幕的右边缘
 * 公式：y=a*x^2
 */

public class PointTypeEvaluator implements TypeEvaluator<CirclePoint> {
    int mHorizontalSpeed = 200;
    int mVerticalSpeed = 200;
    float mDurationTime;
    int mTypeValue;
    float mAcceleration = 0.001f;

    @Override
    public CirclePoint evaluate(float fraction, CirclePoint startValue, CirclePoint endValue) {
        //  Log.i("fraction====", fraction + "=======startValue===" + startValue+ "=====endValue===" + endValue.getPointX());
        CirclePoint mCirclePoint = new CirclePoint();
        float moveX;
        float moveY;
        float startX = 0, startY = 0;
        float endX = 0;
        if (null != startValue) {
            startX = startValue.getPointX();
            startY = startValue.getPointY();
        }

        if (null != endValue) {
            endX = endValue.getPointX();
        }
        if (mTypeValue == 1) {
            float currentTime = mDurationTime * fraction;
            moveX = currentTime * mHorizontalSpeed + startX;
            moveY = currentTime * currentTime * mVerticalSpeed + startY;
        } else {
            moveX = startX + (endX - startX) * fraction;
            moveY = startY+mAcceleration * moveX * moveX;
        }
        mCirclePoint.setPointX(moveX);
        mCirclePoint.setPointY(moveY);
        return mCirclePoint;
    }

    public void setDurationTime(float durationTime) {
        mDurationTime = durationTime;

    }

    public void initType(int i) {
        mTypeValue = i;
    }
}
