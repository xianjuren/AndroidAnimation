package com.animation.animation.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Jone on 17/4/24.
 */

public class DensityUtil {

    //如果是直接返回的int,需要在转化结果后添加 0.5f，为了四舍五入
    public static float pxToSp(Context context, float px) {

        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scale;
    }

    public static float spToPx(Context context, float sp) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static float pxToDp(Context context, float px) {

        float scale = context.getResources().getDisplayMetrics().density;
        return px / scale;
    }

    public static float dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale;
    }

    //系统也提供了TypedValue类帮助我们转换

    public static float dpToPxSystem(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float spToPxSystem(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
