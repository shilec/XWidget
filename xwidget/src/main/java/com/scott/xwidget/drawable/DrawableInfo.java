package com.scott.xwidget.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;


public class DrawableInfo {
    public int solidColor;
    public float corner;
    public float strokeBorderWith;
    public int strokeBorderColor;
    public float gradientRadius;
    public int gradientStartColor;
    public int gradientCenterColor;
    public int gradientEndColor;
    public int gradientType;
    public int gradientOrientation;
    public int cornerType;
    public float shadowRadius;
    public int shadowColor;
    public float shadowDx;
    public float shadowDy;
    public float blurRadius;
    public int blurColor;
    public int blurType;
    public Drawable normalDrawable;

    public DrawableInfo() {

    }

    public void merge(DrawableInfo drawableInfo) {
        if (solidColor == 0 && drawableInfo.solidColor != 0) {
            solidColor = drawableInfo.solidColor;
        }

        if (corner == 0 && drawableInfo.corner != 0) {
            corner = drawableInfo.corner;
        }

        if (strokeBorderWith == 0 && drawableInfo.strokeBorderWith != 0) {
            strokeBorderWith = drawableInfo.strokeBorderWith;
        }

        if (strokeBorderColor == 0 && drawableInfo.strokeBorderColor != 0) {
            strokeBorderColor = drawableInfo.strokeBorderColor;
        }

        if (gradientRadius == 0 && drawableInfo.gradientRadius != 0) {
            gradientRadius = drawableInfo.gradientRadius;
        }

        if (gradientStartColor == 0 && drawableInfo.gradientStartColor != 0) {
            gradientStartColor = drawableInfo.gradientStartColor;
        }
        if (gradientCenterColor == 0 && drawableInfo.gradientCenterColor != 0) {
            gradientCenterColor = drawableInfo.gradientCenterColor;
        }
        if (gradientEndColor == 0 && drawableInfo.gradientEndColor != 0) {
            gradientEndColor = drawableInfo.gradientEndColor;
        }
        if (gradientType == 0 && drawableInfo.gradientType != 0) {
            gradientType = drawableInfo.gradientType;
        }
        if (gradientOrientation == 0 && drawableInfo.gradientOrientation != 0) {
            gradientOrientation = drawableInfo.gradientOrientation;
        }

        if (cornerType == 0 && drawableInfo.cornerType != 0) {
            cornerType = drawableInfo.cornerType;
        }
        if (shadowRadius == 0 && drawableInfo.shadowRadius != 0) {
            shadowRadius = drawableInfo.shadowRadius;
        }
        if (shadowColor == 0 && drawableInfo.shadowColor != 0) {
            shadowColor = drawableInfo.shadowColor;
        }
        if (shadowDx == 0 && drawableInfo.shadowDx != 0) {
            shadowDx = drawableInfo.shadowDx;
        }

        if (shadowDy == 0 && drawableInfo.shadowDy != 0) {
            shadowDy = drawableInfo.shadowDy;
        }
        if (blurRadius == 0 && drawableInfo.blurRadius != 0) {
            blurRadius = drawableInfo.blurRadius;
        }

        if (blurColor == 0 && drawableInfo.blurColor != 0) {
            blurColor = drawableInfo.blurColor;
        }

        if (blurType == 0 && drawableInfo.blurType != 0) {
            blurType = drawableInfo.blurType;
        }

        if (drawableInfo.normalDrawable != null) {
            normalDrawable = drawableInfo.normalDrawable;
        }
    }


    static GradientDrawable.Orientation getOrientation(int orientation) {
        GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
        switch (orientation) {
            case 1:
                gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 2:
                gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 3:
                gradientOrientation = GradientDrawable.Orientation.TL_BR;
                break;
            case 4:
                gradientOrientation = GradientDrawable.Orientation.TR_BL;
                break;
        }
        return gradientOrientation;
    }
}
