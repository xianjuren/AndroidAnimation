package com.animation.animation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.animation.animation.adpter.FlickerAdapter;
import com.animation.animation.R;
import com.animation.animation.bean.Coupon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jone on 17/4/20.
 */
public class FlickerViewActivity extends BaseBackActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView mRecycleView = (RecyclerView) findViewById(R.id.flicker_recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FlickerAdapter mFlickerAdapter = new FlickerAdapter(this);
        mRecycleView.setAdapter(mFlickerAdapter);
        List<Coupon> mCoupons = getListCoupon();
        mFlickerAdapter.setData(mCoupons);
    }

    private List<Coupon> getListCoupon() {
        List<Coupon> mCoupons = new ArrayList<>(4);

        mCoupons.add(new Coupon("5.00", "直减优惠券", false));
        mCoupons.add(new Coupon("8.00", "满减优惠券", false));
        mCoupons.add(new Coupon("16.00", "特种优惠券", false));
        mCoupons.add(new Coupon("278.00", "积分优惠券", false));
        return mCoupons;
    }

    @Override
    int getContentLayoutId() {
        return R.layout.activity_flicker_view;
    }

    @Override
    int getActivityTitle() {
        return R.string.flicker_view;
    }
}
