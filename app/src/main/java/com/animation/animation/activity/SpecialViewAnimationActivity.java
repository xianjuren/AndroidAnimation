package com.animation.animation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.animation.animation.R;

/**
 * Created by Jone on 17/4/21.
 */
public class SpecialViewAnimationActivity extends BaseBackActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void overrideActivity(View view){
        startActivity(new Intent(this,OverrideActivity.class));
        overridePendingTransition(R.anim.activity_into,R.anim.activity_out);
    }


    public void layoutAnimation(View view){

    }

    @Override
    int getContentLayoutId() {
        return R.layout.actiivty_special_view;
    }

    @Override
    int getActivityTitle() {
        return R.string.special_view_animation;
    }
}
