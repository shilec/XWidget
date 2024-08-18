package com.scott.xwidget.template;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;

import com.scott.xwidget.IWidgetParser;
import com.scott.xwidget.R;
import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.GradientDrawableDecorator;
import com.scott.xwidget.drawable.RippleDrawableDecorator;
import com.scott.xwidget.drawable.StateListDrawableDecorator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XViewParserTemplate implements IWidgetParser {

    public XViewParserTemplate() {
    }

    @Nullable
    @Override
    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {
        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XViewTemplate);
        TypedArray styleArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XViewTemplate, 0, 0);

        int type = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_state, 0);

        Drawable normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_drawable);
        if (type == 0 && normalDrawable != null) {
            recycleTypedArrays(arr, styleArray);
            return normalDrawable;
        }

        Drawable stateDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_drawable);
        if (normalDrawable != null && stateDrawable != null) {
            recycleTypedArrays(arr, styleArray);
            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
        }

        boolean rippleEnable = getBooleanAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_ripple_enable, false);
        int rippleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_ripple_color, 0);

        DrawableInfo mNormal = fromNormalTypeArray(arr, styleArray);
        mNormal.state = type;
        mNormal.rippleEnable = rippleEnable;
        mNormal.rippleColor = rippleColor;

        Drawable drawableResult;
        if (type == 0) {
            drawableResult = rippleEnable ? new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new GradientDrawableDecorator(mNormal), null, mNormal)
                    : new GradientDrawableDecorator(mNormal);
        } else {
            DrawableInfo mStated = fromStatedTypeArray(arr, styleArray);
            mStated.merge(mNormal);
            mStated.state = type;

            normalDrawable = new GradientDrawableDecorator(mNormal);
            stateDrawable = new GradientDrawableDecorator(mStated);

            drawableResult = rippleEnable ? new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new StateListDrawableDecorator(type, normalDrawable, stateDrawable), null, mNormal)
                    : new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
        }

        recycleTypedArrays(arr, styleArray);
        return drawableResult;
    }

    private int getIntAttribute(TypedArray arr, TypedArray styleArray, int index, int defaultValue) {
        return arr.hasValue(index) ? arr.getInt(index, defaultValue) : styleArray.getInt(index, defaultValue);
    }

    private boolean getBooleanAttribute(TypedArray arr, TypedArray styleArray, int index, boolean defaultValue) {
        return arr.hasValue(index) ? arr.getBoolean(index, defaultValue) : styleArray.getBoolean(index, defaultValue);
    }

    private int getColorAttribute(TypedArray arr, TypedArray styleArray, int index, int defaultValue) {
        return arr.hasValue(index) ? arr.getColor(index, defaultValue) : styleArray.getColor(index, defaultValue);
    }

    private Drawable getDrawableAttribute(TypedArray arr, TypedArray styleArray, int index) {
        return arr.hasValue(index) ? arr.getDrawable(index) : styleArray.getDrawable(index);
    }

    private void recycleTypedArrays(TypedArray arr, TypedArray styleArray) {
        arr.recycle();
        styleArray.recycle();
    }

    public DrawableInfo fromNormalTypeArray(TypedArray arr, TypedArray styleArray) {
        DrawableInfo drawableInfo = new DrawableInfo();

        drawableInfo.solidColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_solid_color, Color.TRANSPARENT);
        drawableInfo.corner = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_corner, 0F);
        drawableInfo.strokeBorderWith = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_border, 0F);
        drawableInfo.strokeBorderColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_color, Color.TRANSPARENT);
        drawableInfo.gradientRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_radius, 0F);
        drawableInfo.gradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_solid_gradient, 0);
        drawableInfo.gradientOrientation = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_orientation, 0);
        drawableInfo.cornerType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_corner_type, 0);
        drawableInfo.shadowRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_radius, 0f);
        drawableInfo.shadowColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_color, 0);
        drawableInfo.shadowDx = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_dx, 0f);
        drawableInfo.shadowDy = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_dy, 0f);
        drawableInfo.blurRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_radius, 0f);
        drawableInfo.blurColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_color, 0);
        drawableInfo.blurType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_type, 0);
        drawableInfo.normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_drawable);
        drawableInfo.strokeGradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_start_color, 0);
        drawableInfo.strokeGradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_end_color, 0);
        drawableInfo.strokeGradientMiddleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_middle_color, 0);
        drawableInfo.strokeGradientOffsetX = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x, 0f);
        drawableInfo.strokeGradientOffsetX1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x1, 0f);
        drawableInfo.strokeGradientOffsetY = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y, 0f);
        drawableInfo.strokeGradientOffsetY1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y1, 0f);
        drawableInfo.textColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_text_color, 0);
        drawableInfo.colorFilter = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_color_filter, -1);

        return drawableInfo;
    }

    public DrawableInfo fromStatedTypeArray(TypedArray arr, TypedArray styleArray) {
        DrawableInfo drawableInfo = new DrawableInfo();

        drawableInfo.solidColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_solid_color, Color.TRANSPARENT);
        drawableInfo.corner = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_corner, 0F);
        drawableInfo.strokeBorderWith = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_border, 0F);
        drawableInfo.strokeBorderColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_color, Color.TRANSPARENT);
        drawableInfo.gradientRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_radius, 0F);
        drawableInfo.gradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_solid_gradient, 0);
        drawableInfo.gradientOrientation = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_orientation, 0);
        drawableInfo.cornerType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_corner_type, 0);
        drawableInfo.shadowRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_radius, 0f);
        drawableInfo.shadowColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_color, 0);
        drawableInfo.shadowDx = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dx, 0f);
        drawableInfo.shadowDy = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dy, 0f);
        drawableInfo.blurRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_radius, 0f);
        drawableInfo.blurColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_color, 0);
        drawableInfo.blurType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_type, 0);
        drawableInfo.normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_drawable);
        drawableInfo.strokeGradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_start_color, 0);
        drawableInfo.strokeGradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_end_color, 0);
        drawableInfo.strokeGradientMiddleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_middle_color, 0);
        drawableInfo.strokeGradientOffsetX = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x, 0f);
        drawableInfo.strokeGradientOffsetX1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x1, 0f);
        drawableInfo.strokeGradientOffsetY = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y, 0f);
        drawableInfo.strokeGradientOffsetY1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y1, 0f);
        drawableInfo.textColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_text_color, 0);
        drawableInfo.colorFilter = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_color_filter, -1);

        return drawableInfo;
    }

    private float getDimensionAttribute(TypedArray arr, TypedArray styleArray, int index, float defaultValue) {
        return arr.hasValue(index) ? arr.getDimension(index, defaultValue) : styleArray.getDimension(index, defaultValue);
    }
}
