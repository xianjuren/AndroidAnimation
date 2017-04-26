package com.animation.animation.bean;

/**
 * Created by Jone on 17/4/26.
 */

public class CirclePoint {

    private float pointX;
    private float pointY;

    public CirclePoint(float pointX, float pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public CirclePoint() {

    }

    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }
}
