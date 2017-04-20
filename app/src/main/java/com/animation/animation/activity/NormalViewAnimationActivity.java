package com.animation.animation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.animation.animation.R;


/**
 * Created by Jone on 17/4/18.
 */
public class NormalViewAnimationActivity extends BaseBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void flicker(View view) {
        startActivity(new Intent(this, FlickerViewActivity.class));
    }


    public void rotate(View view) {
        startActivity(new Intent(this, RotateViewActivity.class));
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_normal_view_animation;
    }

    @Override
    int getActivityTitle() {
        return R.string.normal_view_animation;
    }
}
