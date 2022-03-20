package com.scott.xwidget.drawable;


import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface IDrawableEditBuilder {
    /**
     * @see DrawableInfo#corner
     */
    IDrawableEditBuilder setCorner(float corner);

    /**
     * @see DrawableInfo#cornerType
     */
    IDrawableEditBuilder setCornerType(int cornerType);

    @IntDef({

            CornerType.ALL,
            CornerType.LEFT_BOTTOM,
            CornerType.LEFT_TOP,
            CornerType.RIGHT_TOP,
            CornerType.RIGHT_BOTTOM,
            CornerType.LEFT_TOP | CornerType.RIGHT_TOP | CornerType.LEFT_BOTTOM | CornerType.RIGHT_BOTTOM,
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @interface CornerType {
        int LEFT_TOP = 0X01;
        int RIGHT_TOP = 0X08;
        int LEFT_BOTTOM = 0X16;
        int RIGHT_BOTTOM = 0x32;
        int ALL = 0;
    }

    /**
     * @see DrawableInfo#blurType
     */
    IDrawableEditBuilder setBlurType(@BlurType int blurType);

    @IntDef({
            BlurType.NORMAL,
            BlurType.OUTER,
            BlurType.SOLID,
            BlurType.INNER
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @interface BlurType {
        int NORMAL = 0;
        int OUTER = 1;
        int SOLID = 2;
        int INNER = 3;
    }

    /**
     * @see DrawableInfo#blurRadius
     */
    IDrawableEditBuilder setBlurRadius(float r);

    /**
     * @see DrawableInfo#blurColor
     */
    IDrawableEditBuilder setBlurColor(int color);

    /**
     * @see DrawableInfo#shadowRadius
     */
    IDrawableEditBuilder setShadowRadius(float r);

    /**
     * @see DrawableInfo#shadowDx
     */
    IDrawableEditBuilder setShadowDx(int dx);

    /**
     * @see DrawableInfo#shadowDy
     */
    IDrawableEditBuilder setShadowDy(int dy);

    /**
     * @see DrawableInfo#shadowColor
     */
    IDrawableEditBuilder setShadowColor(int shadowColor);

    /**
     * @see DrawableInfo#solidColor
     */
    IDrawableEditBuilder setSolidColor(int color);

    @IntDef({
            GradientType.LINEAR,
            GradientType.RADIAL,
            GradientType.SWEEP,
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @interface GradientType {
        int LINEAR = 0;
        int RADIAL = 1;
        int SWEEP = 2;
    }

    /**
     * @see DrawableInfo#gradientType
     */
    IDrawableEditBuilder setSolidGradient(@GradientType int type);

    /**
     * @see DrawableInfo#gradientStartColor
     * @see DrawableInfo#gradientCenterColor
     * @see DrawableInfo#gradientEndColor
     */
    IDrawableEditBuilder setGradientColors(int[] colors);


    @IntDef({
            Orientation.TOP_BOTTOM,
            Orientation.LEFT_RIGHT
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @interface Orientation {
        int TOP_BOTTOM = 2;
        int LEFT_RIGHT = 1;
    }

    /**
     * @see DrawableInfo#gradientOrientation
     */
    IDrawableEditBuilder setGradientOrientation(@Orientation int orientation);

    /**
     * @see DrawableInfo#gradientRadius
     */
    IDrawableEditBuilder setGradientRadius(float r);

    /**
     * @see DrawableInfo#strokeBorderColor
     */
    IDrawableEditBuilder setStrokeBorderColor(int color);

    /**
     * @see DrawableInfo#strokeBorderWith
     */
    IDrawableEditBuilder setStrokeBorder(float border);

    /**
     * @see DrawableInfo#strokeGradientStartColor
     * @see DrawableInfo#strokeGradientMiddleColor
     * @see DrawableInfo#strokeGradientEndColor
     */
    IDrawableEditBuilder setStrokeGradientColors(int[] colors);

    /**
     * @see DrawableInfo#strokeGradientOffsetX
     * @see DrawableInfo#strokeGradientOffsetY
     * @see DrawableInfo#strokeGradientOffsetX1
     * @see DrawableInfo#strokeGradientOffsetY1
     */
    IDrawableEditBuilder setStrokeGradientOffset(float[] offset);

    /**
     * @see DrawableInfo#strokeGradientType
    */
    IDrawableEditBuilder setStrokeGradientType(@GradientType int type);

    /**
     * @see DrawableInfo#textColor
     */
    IDrawableEditBuilder setTextColor(int color);

    /**
     * @see DrawableInfo#colorFilter
     */
    IDrawableEditBuilder setImageColorFilter(int color);

    /**
     * @see DrawableInfo#rippleEnable
     */
    IDrawableEditBuilder setRippleEnable(boolean enable);

    /**
     * @see DrawableInfo#rippleColor
     */
    IDrawableEditBuilder setRippleColor(int color);

    /**
     *  commit the drawable changes
     */
    void commit();
}