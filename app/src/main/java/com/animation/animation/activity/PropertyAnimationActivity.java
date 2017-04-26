package com.animation.animation.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.animation.animation.R;
import com.animation.animation.utils.DensityUtil;
import com.animation.animation.view.CustomImage3dVIew;

/**
 * Created by Jone on 17/4/24.
 */
public class PropertyAnimationActivity extends BaseBackActivity {

    RelativeLayout mLayout, mRotate3d;
    ImageView mImageView, mImageView3d;
    ObjectAnimator objectAnimatorX, objectAnimatorRota3d;
    TextView mTextView, mTextView3d;
    CustomImage3dVIew mImage3dVIew;
    private String mContent = "\"白日依山尽，黄河入海流\"";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = (RelativeLayout) findViewById(R.id.property_flicker_layout);
        mRotate3d = (RelativeLayout) findViewById(R.id.property_rotate3d_rl);
        mImage3dVIew = (CustomImage3dVIew) findViewById(R.id.property_rotate3d_custom_iv);
        mTextView = (TextView) findViewById(R.id.property_flicker_text);
        mTextView3d = (TextView) findViewById(R.id.property_rotate3d_tv);
        mImageView = (ImageView) findViewById(R.id.property_flicker_picture);
        mImageView3d = (ImageView) findViewById(R.id.property_rotate3d_iv);
    }


    public void propertyFlicker(View view) {
        initAnimation(view.getWidth());
        if (mLayout.getVisibility() == View.VISIBLE) {
            objectAnimatorX.cancel();
            mLayout.setVisibility(View.GONE);
        } else {
            mLayout.setVisibility(View.VISIBLE);
            objectAnimatorX.start();
        }
    }

    private void initAnimation(int width) {
        objectAnimatorX = ObjectAnimator.ofFloat(mImageView, "translationX", -width / 3, width);
        objectAnimatorX.setDuration(500);
        objectAnimatorX.setInterpolator(new LinearInterpolator());
        objectAnimatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mTextView.setText(mContent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        objectAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = animation.getAnimatedFraction();
                mTextView.setText(mContent + " " + percent * 100 + "%");
            }
        });
    }

    public void propertyRotate(View view) {//synthesized attribute
        startActivity(new Intent(this, SynthesizedAttributeActivity.class));
    }


    public void propertyRotate3d(View view) {
        //方式1
//        initAnimationRate3d(view);
//        if (mRotate3d.getVisibility() == View.VISIBLE) {
//            objectAnimatorRota3d.cancel();
//            mRotate3d.setVisibility(View.GONE);
//        } else {
//            mRotate3d.setVisibility(View.VISIBLE);
//            objectAnimatorRota3d.start();
//        }

        //方式2
        if (mRotate3d.getVisibility() == View.VISIBLE) {
            mRotate3d.setVisibility(View.GONE);
            mImage3dVIew.startRotate(0);
        } else {
            mRotate3d.setVisibility(View.VISIBLE);
            mImage3dVIew.startRotate(10000);
        }
    }

    private void initAnimationRate3d(final View view) {
        //效果不是特别理想，需要把执行时间设置较长
        final float pictureHeight = DensityUtil.dpToPx(this, 100);
        objectAnimatorRota3d = ObjectAnimator.ofFloat(mImageView3d, "rotationX", 0, 180, 270, 360);
        objectAnimatorRota3d.setDuration(6000);
        objectAnimatorRota3d.setInterpolator(new OvershootInterpolator());
        objectAnimatorRota3d.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //以外部参照为“公转”，在控件自转的时候添加一个同步的位移效果（如下y轴），这样整体效果看起来像是控件在绕侧边旋转一样。
                float degrees = (float) animation.getAnimatedValue();
                //旋转180，滚动一个Height
                int n = (int) (degrees / 180);
                double cons = Math.cos(Math.toRadians(degrees - n * 180));
                float transY = (float) ((1 + -cons) * pictureHeight) / 2 + n * pictureHeight;
                mImageView3d.setTranslationY(transY);
            }
        });
    }

    public void layoutTransition(View view) {
        startActivity(new Intent(this, LayoutTransitionActivity.class));
    }

    public void customTypeEvaluator(View view) {
        startActivity(new Intent(this, CustomTypeEvaluatorActivity.class));
    }

    public void customPropertyName(View view) {
        startActivity(new Intent(this, CustomPropertyNameActivity.class));
    }

    public void keyFrame(View view){
        startActivity(new Intent(this, KeyframeActivity.class));
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_property;
    }

    @Override
    int getActivityTitle() {
        return R.string.property_animation;
    }
}
