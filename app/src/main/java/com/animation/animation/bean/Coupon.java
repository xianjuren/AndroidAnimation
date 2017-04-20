package com.animation.animation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jone on 17/4/20.
 */
public class Coupon implements Parcelable {

    private String price ;

    private String kind;

    private boolean mFirstShow;


    public Coupon(String price, String kind,boolean firstShow) {
        this.price = price;
        this.kind = kind;
        this.mFirstShow =firstShow;
    }

    public boolean isFirstShow() {
        return mFirstShow;
    }

    public void setFirstShow(boolean firstShow) {
        mFirstShow = firstShow;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.price);
        dest.writeString(this.kind);
        dest.writeByte(this.mFirstShow ? (byte) 1 : (byte) 0);
    }

    protected Coupon(Parcel in) {
        this.price = in.readString();
        this.kind = in.readString();
        this.mFirstShow = in.readByte() != 0;
    }

    public static final Creator<Coupon> CREATOR = new Creator<Coupon>() {
        @Override
        public Coupon createFromParcel(Parcel source) {
            return new Coupon(source);
        }

        @Override
        public Coupon[] newArray(int size) {
            return new Coupon[size];
        }
    };
}
