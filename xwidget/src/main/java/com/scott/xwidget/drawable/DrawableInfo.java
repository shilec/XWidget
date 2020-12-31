package com.scott.xwidget.drawable;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.scott.xwidget.R;

public class DrawableInfo {
    int solidColor;
    float corner;
    float strokeBorderWith;
    int strokeBorderColor;
    float gradientRadius;
    int gradientStartColor;
    int gradientCenterColor;
    int gradientEndColor;
    int gradientType;
    int gradientOrientation;
    int cornerType;
    float shadowRadius;
    int shadowColor;
    float shadowDx;
    float shadowDy;
    float blurRadius;
    int blurColor;
    int blurType;
    Drawable normalDrawable;

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

    public static DrawableInfo fromNormalTypeArray(TypedArray arr) {
        DrawableInfo drawableInfo = new DrawableInfo();

        // 填充色
        drawableInfo.solidColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_solid_color, Color.TRANSPARENT);
        // 角度
        drawableInfo.corner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_corner, 0F);
        // 描边的宽度
        drawableInfo.strokeBorderWith = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stroke_border, 0F);
        // 描边颜色
        drawableInfo.strokeBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stroke_color, Color.TRANSPARENT);
        // 渐变填充的角度
        drawableInfo.gradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_gradient_radius, 0F);
        // 渐变开始颜色
        drawableInfo.gradientStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_solid_gradient, 0);
        drawableInfo.gradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_gradient_orientation, 0);
        drawableInfo.cornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_corner_type, 0);
        drawableInfo.shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_radius, 0f);

        drawableInfo. shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_shadow_color, 0);
        drawableInfo.shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dx, 0f);
        drawableInfo.shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dy, 0f);

        drawableInfo.blurRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_blur_radius, 0f);
        drawableInfo.blurColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_blur_color, 0);
        drawableInfo.blurType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_blur_type, 0);

        drawableInfo.normalDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_drawable);
        return drawableInfo;
    }

    public static DrawableInfo fromStatedTypeArray(TypedArray arr) {
        DrawableInfo drawableInfo = new DrawableInfo();

        // 填充色
        drawableInfo.solidColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_color, Color.TRANSPARENT);
        // 角度
        drawableInfo.corner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner, 0F);
        // 描边的宽度
        drawableInfo.strokeBorderWith = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_border, 0F);
        // 描边颜色
        drawableInfo.strokeBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_color, Color.TRANSPARENT);
        // 渐变填充的角度
        drawableInfo.gradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_radius, 0F);
        // 渐变开始颜色
        drawableInfo.gradientStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_gradient, 0);
        drawableInfo.gradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_orientation, 0);
        drawableInfo.cornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner_type, 0);
        drawableInfo.shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_radius, 0f);

        drawableInfo. shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_color, 0);
        drawableInfo.shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dx, 0f);
        drawableInfo.shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dy, 0f);

        drawableInfo.blurRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_radius, 0f);
        drawableInfo.blurColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_color, 0);
        drawableInfo.blurType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_type, 0);

        drawableInfo.normalDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_stated_drawable);
        return drawableInfo;
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
        }
        return gradientOrientation;
    }
}
