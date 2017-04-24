package com.animation.animation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.animation.animation.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout mViewAnimationContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置沉浸式状态栏或者自定义Theme，需要单独添加values-v19
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_main);
        mViewAnimationContent = (LinearLayout) findViewById(R.id.view_Animation_content_layout);
    }


    public void viewAnimation(View view) {
        if (mViewAnimationContent.getVisibility() == View.VISIBLE) {
            mViewAnimationContent.setVisibility(View.GONE);
        } else {
            mViewAnimationContent.setVisibility(View.VISIBLE);
        }
    }

    public void normalViewAnimation(View view) {
        startActivity(new Intent(this, NormalViewAnimationActivity.class));

    }

    public void specialViewAnimation(View view) {
        startActivity(new Intent(this, SpecialViewAnimationActivity.class));
    }

    public void frameAnimation(View view) {
        startActivity(new Intent(this, FrameAnimationActivity.class));
    }

    public void propertyAnimation(View view) {
        startActivity(new Intent(this, PropertyAnimationActivity.class));
    }
}
