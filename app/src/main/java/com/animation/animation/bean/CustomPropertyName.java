package com.animation.animation.bean;

import android.util.Property;
import android.widget.TextView;

/**
 * Created by Jone on 17/4/26.
 */

public class CustomPropertyName extends Property<TextView, Float> {
    public CustomPropertyName(Class<Float> type, String name) {
        super(type, name);
    }

    @Override
    public void set(TextView tv, Float alpha) {
        tv.getLayoutParams().width=  alpha.intValue();
        tv.requestLayout();
    }

    @Override
    public Float get(TextView tv) {
        return Float.valueOf(tv.getLayoutParams().width);
    }
}
