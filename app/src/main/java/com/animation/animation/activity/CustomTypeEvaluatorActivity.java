package com.animation.animation.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.animation.animation.R;
import com.animation.animation.bean.CirclePoint;
import com.animation.animation.view.typeEvalueator.PointTypeEvaluator;

/**
 * Created by Jone on 17/4/26.
 * 实现抛物线轨迹，一种是 固定时间的抛物线，一种是 固定长度的抛物线。
 * * 球按照抛物线轨迹运行1.5秒
 */
public class CustomTypeEvaluatorActivity extends BaseBackActivity {

    ImageView mCircle;
    PointTypeEvaluator mTypeEvaluator;
    float mX, mY;
    boolean mInit;
    int mTypeValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircle = (ImageView) findViewById(R.id.circle_iv);
        mTypeEvaluator = new PointTypeEvaluator();
        //初始化圆的时间
        mTypeEvaluator.setDurationTime(1.5f);
    }


    public void fixedTime(View view) {
        mTypeValue = 1;
        startAnimation();
    }

    private void startAnimation() {
        CirclePoint mEndCirclePoint = new CirclePoint();
        mTypeEvaluator.initType(mTypeValue);
        if (!mInit) {
            mX = mCircle.getX();
            mY = mCircle.getY();
            mInit = true;
        }

        if (mTypeValue == 2) {
            mEndCirclePoint.setPointX(getResources().getDisplayMetrics().widthPixels - mCircle.getWidth());
        } else {
            mEndCirclePoint = null;
        }

        ValueAnimator valueAnimator = ValueAnimator.ofObject(mTypeEvaluator, new CirclePoint(mX, mY), mEndCirclePoint).setDuration(1500);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // float fraction =animation.getAnimatedFraction();
                CirclePoint mCirclePoint = (CirclePoint) animation.getAnimatedValue();
                mCircle.setX(mCirclePoint.getPointX());
                mCircle.setY(mCirclePoint.getPointY());
            }
        });
    }

    public void fixedLength(View view) {
        mTypeValue = 2;
        startAnimation();
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_custom_type_evaluator;
    }

    @Override
    int getActivityTitle() {
        return R.string.property_type_evaluator;
    }
}
