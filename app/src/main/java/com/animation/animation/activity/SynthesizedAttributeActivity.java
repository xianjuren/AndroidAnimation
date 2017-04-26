package com.animation.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.animation.animation.R;
import com.animation.animation.utils.DensityUtil;

/**
 * Created by Jone on 17/4/25.
 */
public class SynthesizedAttributeActivity extends BaseBackActivity {

    RelativeLayout mWindmillLayout, mCleanLayout;
    ImageView mBackground, mWindmill, mWindmillLeft, mWindmillRight, mWindmillBg;
    LinearLayout mIconsLayout, mAdLayout;
    View mExtendedLine;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindmillLayout = (RelativeLayout) findViewById(R.id.windmill_layout);
        mBackground = (ImageView) findViewById(R.id.rotating_circle_bg);
        mWindmill = (ImageView) findViewById(R.id.rotating_windmill);
        mWindmillLeft = (ImageView) findViewById(R.id.windmill_left);
        mWindmillRight = (ImageView) findViewById(R.id.windmill_right);
        mWindmillBg = (ImageView) findViewById(R.id.windmill_bg);
        mIconsLayout = (LinearLayout) findViewById(R.id.icons_layout);
        mAdLayout = (LinearLayout) findViewById(R.id.ad_item);
        mExtendedLine = findViewById(R.id.view_extended_line);
        mCleanLayout = (RelativeLayout) findViewById(R.id.clean_icons_layout);
        initAnimation();

    }

    private void initIcons() {
        float width = DensityUtil.dpToPxSystem(this, 40);
        mIconsLayout.removeAllViews();
        AnimatorSet mSet = new AnimatorSet();
        for (int i = 0; i < 4; i++) {
            ImageView mImageView = new ImageView(this);
            mImageView.setImageResource(R.mipmap.ic_launcher);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP;
            params.width = 0;
            params.weight = 1;
            params.topMargin = (int) width;
            params.height = (int) width;
            mImageView.setLayoutParams(params);
            mIconsLayout.addView(mImageView);
            ObjectAnimator shakeAnimator = ObjectAnimator.ofFloat(mImageView, "translationY", -5, 5).setDuration(100);
            shakeAnimator.setRepeatMode(ValueAnimator.RESTART);
            shakeAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mSet.playTogether(shakeAnimator);
        }
        mSet.setInterpolator(new LinearInterpolator());
        mSet.start();
        removeIconAnimation();
    }

    private void removeIconAnimation() {

        if (mIconsLayout.getChildCount() - 1 >= count) {
            final ImageView imageView = (ImageView) mIconsLayout.getChildAt(count);
            int moveX = mIconsLayout.getWidth() / 2 - imageView.getWidth() * count;
            int moveY = (getResources().getDisplayMetrics().heightPixels - mWindmillLeft.getHeight()) / 2 - imageView.getHeight();
            imageView.clearAnimation();
            imageView.animate().setDuration(200).translationX(moveX).translationY(moveY).alpha(0).setInterpolator(new AccelerateInterpolator()).start();
            imageView.animate().setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    removeIconAnimation();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            count++;
        } else {

            mCleanLayout.animate().scaleX(0).scaleY(0).alpha(0).setDuration(200).start();
            mCleanLayout.animate().setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mCleanLayout.setVisibility(View.GONE);
                    mAdLayout.setVisibility(View.VISIBLE);
                    PropertyValuesHolder adX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
                    PropertyValuesHolder adY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
                    ObjectAnimator mAD = ObjectAnimator.ofPropertyValuesHolder(mAdLayout, adX, adY).setDuration(1000);
                    mAD.setInterpolator(new BounceInterpolator());
                    mAD.start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        }
    }

    private void startCleanAnimation() {
        mWindmillLayout.setVisibility(View.GONE);
        mCleanLayout.setVisibility(View.VISIBLE);
        float marginWidth = DensityUtil.dpToPxSystem(this, 40);
        float moveX = mCleanLayout.getMeasuredWidth() / 2 - marginWidth;
        ObjectAnimator windmillLeft = ObjectAnimator.ofFloat(mWindmillLeft, "translationX", -moveX);
        ObjectAnimator windmillRight = ObjectAnimator.ofFloat(mWindmillRight, "translationX", moveX);
        mExtendedLine.setVisibility(View.VISIBLE);
        ObjectAnimator extendedLine = ObjectAnimator.ofFloat(mExtendedLine, "scaleX", 0, 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(windmillLeft, windmillRight, extendedLine);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mWindmillBg.setVisibility(View.VISIBLE);
                mWindmillBg.animate().setInterpolator(new LinearInterpolator()).scaleY(1).translationY(0).setDuration(200).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mWindmillBg.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                initIcons();
                mIconsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void initAnimation() {
        //第一种方式PropertyValuesHolder
        PropertyValuesHolder mScaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f);
        PropertyValuesHolder mScaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f);
        PropertyValuesHolder mWindmillHolder = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
        ObjectAnimator backgroundAnimator = ObjectAnimator.ofPropertyValuesHolder(mBackground, mScaleYHolder, mScaleXHolder, mWindmillHolder).setDuration(800);
        backgroundAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mWindmill.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        //  backgroundAnimator.start();
        //第二种 ViewPropertyAnimator,缩放、透明设置值都为1时不适合使用该方法
        // mBackground.animate().scaleX(1.2f).scaleY(1.2f).alpha(1).setInterpolator(new LinearInterpolator()).setDuration(800).start();

        ObjectAnimator windmillAnimator = ObjectAnimator.ofPropertyValuesHolder(mWindmill, mScaleYHolder, mScaleXHolder, mWindmillHolder).setDuration(800);
        ObjectAnimator mRotateAnimator = ObjectAnimator.ofFloat(mWindmill, "rotation", 0, 360).setDuration(800);
        mRotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        mRotateAnimator.setRepeatCount(10);//ValueAnimator.INFINITE
        mRotateAnimator.setStartDelay(50);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playSequentially(backgroundAnimator, windmillAnimator, mRotateAnimator);
        animatorSet.start();
        final ViewPropertyAnimator propertyAnimator = mWindmillLayout.animate();
        propertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mWindmillLayout.setVisibility(View.GONE);
                mCleanLayout.setVisibility(View.VISIBLE);
                startCleanAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mRotateAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                propertyAnimator.scaleX(0).scaleY(0).alpha(0).setInterpolator(new LinearInterpolator()).setDuration(800).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    int getContentLayoutId() {
        return R.layout.actiivty_synthesiz_attribute;
    }

    @Override
    int getActivityTitle() {
        return R.string.property_rotate;
    }
}
