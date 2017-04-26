package com.animation.animation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.animation.animation.R;

/**
 * Created by Jone on 17/4/24.
 */

public class CustomImage3dVIew extends ImageView {
    Camera mCamera;
    Matrix mMatrix;
    float mDegrees = 0f, mPercent;
    long mAnimationDuration = 0, mCurrentTime, mLastAnimationTime = 0;
    Bitmap mBitmap;
    Paint mPaint;
    int lastMouseX;
    int lastMouseY;
    boolean mFinishAutoAnimation;
    private int deltaX, deltaY, mCenterX, mCenterY; //翻转角度差值


    public CustomImage3dVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCamera = new Camera();
        mMatrix = new Matrix();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo3);
        mCenterX = mBitmap.getWidth() / 2;
        mCenterY = mBitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        prepareDraw();
        setCanvas(canvas, mDegrees);
        if (mAnimationDuration != 0) {
            invalidate();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastMouseX = x;
                lastMouseY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - lastMouseX;
                int dy = y - lastMouseY;
                deltaX += dx;
                deltaY += dy;
                break;
        }

        invalidate();
        return true;
    }

    private void prepareDraw() {
        mCurrentTime = System.currentTimeMillis();
        if (mLastAnimationTime == 0) {
            mLastAnimationTime = mCurrentTime;
        }
        long durationTime = mCurrentTime - mLastAnimationTime;
        if (durationTime < mAnimationDuration) {
            mPercent = (float) durationTime / mAnimationDuration;
        } else {
            mPercent = 1;
        }
        mDegrees = 180 * mPercent;
        //  Log.i("mPercent=====", mPercent + "====mDegrees====" + mDegrees + "====mCurrentTime===" + mCurrentTime + "====durationTime==" + durationTime);
    }

    private void setCanvas(Canvas canvas, float degrees) {

        canvas.save();
        mCamera.save();
        if (mFinishAutoAnimation) {
            //绕X轴翻转
            mCamera.rotateX(-deltaY);
            //绕Y轴翻转
            mCamera.rotateY(deltaX);
        } else {
            mCamera.rotateX(mDegrees);;
            mCamera.translate(0f,0f,30*(1-mPercent));
        }
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);
        canvas.concat(mMatrix);
        canvas.drawBitmap(mBitmap, mMatrix, null);
        canvas.restore();
        if (degrees >= 180) {
            mAnimationDuration = 0;
            mFinishAutoAnimation = true;
        }
    }


    public void startRotate(long duration) {
        if (duration != 0) {
            mAnimationDuration = duration;
            invalidate();
        }
    }
}
