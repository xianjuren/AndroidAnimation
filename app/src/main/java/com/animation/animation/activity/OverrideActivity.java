package com.animation.animation.activity;

import android.app.Activity;
import android.os.Bundle;

import com.animation.animation.R;

/**
 * Created by Jone on 17/4/21.
 */
public class OverrideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_override);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_into, R.anim.activity_out);
    }
}
