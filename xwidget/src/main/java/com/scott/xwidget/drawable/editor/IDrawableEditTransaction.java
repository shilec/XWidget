package com.scott.xwidget.drawable.editor;


import androidx.annotation.IntDef;
import androidx.annotation.Keep;

import com.scott.xwidget.drawable.DrawableInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Keep
public interface IDrawableEditTransaction {
    /**
     * 作为 transaction 代理类
     * @param transaction 可以传入一个其他的IDrawableEditTransaction，同步修改
     */
    IDrawableEditTransaction asProxy(IDrawableEditTransaction transaction);

    /**
     * @see DrawableInfo#corner
     */
    IDrawableEditTransaction setCorner(float corner);

    /**
     * @see DrawableInfo#cornerType
     */
    IDrawableEditTransaction setCornerType(int cornerType);

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
    IDrawableEditTransaction setBlurType(@BlurType int blurType);

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
    IDrawableEditTransaction setBlurRadius(float r);

    /**
     * @see DrawableInfo#blurColor
     */
    IDrawableEditTransaction setBlurColor(int color);

    /**
     * @see DrawableInfo#shadowRadius
     */
    IDrawableEditTransaction setShadowRadius(float r);

    /**
     * @see DrawableInfo#shadowDx
     */
    IDrawableEditTransaction setShadowDx(int dx);

    /**
     * @see DrawableInfo#shadowDy
     */
    IDrawableEditTransaction setShadowDy(int dy);

    /**
     * @see DrawableInfo#shadowColor
     */
    IDrawableEditTransaction setShadowColor(int shadowColor);

    /**
     * @see DrawableInfo#solidColor
     */
    IDrawableEditTransaction setSolidColor(int color);

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
    IDrawableEditTransaction setSolidGradient(@GradientType int type);

    /**
     * @see DrawableInfo#gradientStartColor
     * @see DrawableInfo#gradientCenterColor
     * @see DrawableInfo#gradientEndColor
     */
    IDrawableEditTransaction setGradientColors(int[] colors);


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
    IDrawableEditTransaction setGradientOrientation(@Orientation int orientation);

    /**
     * @see DrawableInfo#gradientRadius
     */
    IDrawableEditTransaction setGradientRadius(float r);

    /**
     * @see DrawableInfo#strokeBorderColor
     */
    IDrawableEditTransaction setStrokeBorderColor(int color);

    /**
     * @see DrawableInfo#strokeBorderWith
     */
    IDrawableEditTransaction setStrokeBorder(float border);

    /**
     * @see DrawableInfo#strokeGradientStartColor
     * @see DrawableInfo#strokeGradientMiddleColor
     * @see DrawableInfo#strokeGradientEndColor
     */
    IDrawableEditTransaction setStrokeGradientColors(int[] colors);

    /**
     * @see DrawableInfo#strokeGradientOffsetX
     * @see DrawableInfo#strokeGradientOffsetY
     * @see DrawableInfo#strokeGradientOffsetX1
     * @see DrawableInfo#strokeGradientOffsetY1
     */
    IDrawableEditTransaction setStrokeGradientOffset(float[] offset);

    /**
     * @see DrawableInfo#strokeGradientType
    */
    IDrawableEditTransaction setStrokeGradientType(@GradientType int type);

    /**
     * @see DrawableInfo#textColor
     */
    IDrawableEditTransaction setTextColor(int color);

    /**
     * @see DrawableInfo#colorFilter
     */
    IDrawableEditTransaction setImageColorFilter(int color);

    /**
     * @see DrawableInfo#rippleEnable
     */
    IDrawableEditTransaction setRippleEnable(boolean enable);

    /**
     * @see DrawableInfo#rippleColor
     */
    IDrawableEditTransaction setRippleColor(int color);

    /**
     *  commit the drawable changes
     */
    IDrawableEditor commit();
}