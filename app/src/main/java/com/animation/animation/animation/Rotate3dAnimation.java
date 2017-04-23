package com.animation.animation.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Jone on 17/4/21.
 */

public class Rotate3dAnimation extends Animation {

    public static final int Rotate_X = 0;
    public static final int Rotate_Y = 1;
    public static final int Rotate_Z = 2;
    Camera mCamera;
    boolean mReverse;
    private float mFromDegrees, mToDegrees, mPivotY, mPivotX, mPivotZ, mPreDegrees;
    //默认绝对值坐标
    private int mRotateDirection;

    public Rotate3dAnimation(int rotateDirection, float fromDegrees, float toDegrees, float pivotX, float pivotY, float pivotZ, boolean reverse) {
        mRotateDirection = rotateDirection;
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mPivotY = pivotY;
        mPivotX = pivotX;
        mPivotZ = pivotZ;
        mReverse = reverse;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        final float fromDegrees = mFromDegrees;
        float currentDegrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime;
        if(currentDegrees==mPreDegrees){
            return;
        }
        Matrix matrix = t.getMatrix();
        mCamera.save();
        if (mReverse) {
            mCamera.translate(0.0f, 0.0f, mPivotZ * interpolatedTime);
        } else {
            mCamera.translate(0.0f, 0.0f, mPivotZ * (1.0f - interpolatedTime));
        }
        switch (mRotateDirection) {
            case Rotate_X:
                mCamera.rotateX(currentDegrees);
                break;
            case Rotate_Y:
                mCamera.rotateY(currentDegrees);
                break;
            case Rotate_Z:
                mCamera.rotateZ(currentDegrees);
                break;
        }
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(-mPivotX, -mPivotY);
        matrix.postTranslate(mPivotX, mPivotY);
        mPreDegrees = currentDegrees;
    }


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }
}
