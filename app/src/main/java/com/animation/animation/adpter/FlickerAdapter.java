package com.animation.animation.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.animation.animation.R;
import com.animation.animation.bean.Coupon;

import java.util.List;

/**
 * Created by Jone on 17/4/20.
 */
public class FlickerAdapter extends RecyclerView.Adapter {

    Animation mAnimation;
    private List<Coupon> mCoupons;

    public FlickerAdapter(Context context) {
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.flicker_view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flicker_item, null);
        return new FlickerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FlickerViewHolder mHolder = (FlickerViewHolder) holder;
        if (null != mCoupons && null != mCoupons.get(position)) {
            mHolder.mTvPrice.setText(mCoupons.get(position).getPrice());
            mHolder.mTvCouponName.setText(mCoupons.get(position).getKind());
            mHolder.imageView.setVisibility(View.GONE);
            if (!mCoupons.get(position).isFirstShow() && position == 0) {
                mHolder.imageView.setVisibility(View.VISIBLE);
                startAnimation(mHolder.imageView);
            } else {
                mHolder.imageView.setVisibility(View.GONE);
            }
            mCoupons.get(position).setFirstShow(true);
        }

    }


    private void startAnimation(final ImageView imageView) {
        if (null != mAnimation) {
            imageView.startAnimation(mAnimation);
        }
    }

    @Override
    public int getItemCount() {
        if (mCoupons != null && mCoupons.size() > 0) {
            return mCoupons.size();
        }
        return 0;
    }

    public void setData(List<Coupon> coupons) {
        mCoupons = coupons;
    }

    private class FlickerViewHolder extends RecyclerView.ViewHolder {
        TextView mTvPrice, mTvCouponName;
        ImageView imageView;

        public FlickerViewHolder(View mView) {
            super(mView);
            mTvPrice = (TextView) mView.findViewById(R.id.flicker_item_price);
            mTvCouponName = (TextView) mView.findViewById(R.id.flicker_item_coupon_name);
            imageView = (ImageView) mView.findViewById(R.id.flicker_item_iv);
        }
    }

}