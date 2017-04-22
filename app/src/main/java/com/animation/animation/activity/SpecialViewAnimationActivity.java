package com.animation.animation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.animation.animation.R;
import com.animation.animation.adpter.FlickerAdapter;
import com.animation.animation.bean.Coupon;
import com.animation.animation.utils.SlideItemAnimator;
import com.animation.animation.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jone on 17/4/21.
 */
public class SpecialViewAnimationActivity extends BaseBackActivity {

    RecyclerView mRecycleView;
    TextView mLayoutHint;
    LayoutAnimationController mController;
    FlickerAdapter mFlickerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutHint = (TextView) findViewById(R.id.tv_layout_animation);
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_view_layout);
        mRecycleView.setVisibility(View.VISIBLE);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mFlickerAdapter = new FlickerAdapter(this);

        //设置进入和退出动画
        SlideItemAnimator itemAnimator = new SlideItemAnimator();
        mRecycleView.setAdapter(mFlickerAdapter);
        List<Coupon> mCoupons = getListCoupon();
        mFlickerAdapter.setData(mCoupons);
        mFlickerAdapter.setItemAnimator(itemAnimator);
        mRecycleView.setItemAnimator(itemAnimator);


//        //进入动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.side_to_right);
//        mController = new LayoutAnimationController(animation);
//        mController.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        mController.setDelay(0.5f);
//        mRecycleView.setLayoutAnimation(mController);
    }

    private List<Coupon> getListCoupon() {
        List<Coupon> mCoupons = new ArrayList<>(4);
        mCoupons.add(new Coupon("5.00", "直减优惠券", false));
        mCoupons.add(new Coupon("8.00", "满减优惠券", false));
        mCoupons.add(new Coupon("16.00", "特种优惠券", false));
        mCoupons.add(new Coupon("278.00", "积分优惠券", false));
        return mCoupons;
    }


    public void overrideActivity(View view) {
        startActivity(new Intent(this, OverrideActivity.class));
        overridePendingTransition(R.anim.activity_into, R.anim.activity_out);
    }


    public void layoutAnimation(View view) {

        mFlickerAdapter.removePosition();
//        if (mRecycleView.getVisibility() == View.VISIBLE) {
//            //  mRecycleView.setLayoutAnimation(mController);
//            mFlickerAdapter.removePosition();
//            mLayoutHint.setText(StringUtil.StringId(R.string.start_layout_animation));
//        } else {
//            mLayoutHint.setText(StringUtil.StringId(R.string.end_layout_animation));
//            mRecycleView.setVisibility(View.VISIBLE);
        // }
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
