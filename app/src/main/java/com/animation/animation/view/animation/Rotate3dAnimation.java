package com.animation.animation.view.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.animation.animation.application.GlobalConfig;

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
    float scale = 1;                            // 屏幕密度（默认值为1）
    /**
     * 创建一个新的实例 Rotate3dAnimation.
     *@param  rotateDirection 扭曲方向
     * @param fromDegrees 开始角度
     * @param toDegrees   结束角度
     * @param pivotX     中心点x坐标
     * @param pivotY     中心点y坐标
     * @param pivotZ      深度
     * @param reverse     是否扭曲
     */
    public Rotate3dAnimation(int rotateDirection, float fromDegrees, float toDegrees, float pivotX, float pivotY, float pivotZ, boolean reverse) {
        mRotateDirection = rotateDirection;
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mPivotY = pivotY;
        mPivotX = pivotX;
        mPivotZ = pivotZ;
        mReverse = reverse;
        //获取手机像素比 （即dp与px的比例）
        scale = GlobalConfig.mContext.getResources().getDisplayMetrics().density;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        final float fromDegrees = mFromDegrees;
        float currentDegrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime;

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
//        * 简要介绍：
//        * 原来的3D翻转会由于屏幕像素密度问题而出现效果相差很大
//                * 例如在屏幕像素比为1,5的手机上显示效果基本正常，
//        * 而在像素比3,0的手机上面感觉翻转感觉要超出屏幕边缘，
//        * 有种迎面打脸的感觉、
//        *
//        * 解决方案
//                * 利用屏幕像素密度对变换矩阵进行校正，
//        * 保证了在所有清晰度的手机上显示的效果基本相同。
//        *
        float[] mValues = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        matrix.getValues(mValues);            //获取数值
        mValues[6] = mValues[6] / scale;      //数值修正
        matrix.setValues(mValues);

        //重新赋值
        matrix.preTranslate(-mPivotX, -mPivotY);
        matrix.postTranslate(mPivotX, mPivotY);
    }


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }
}
