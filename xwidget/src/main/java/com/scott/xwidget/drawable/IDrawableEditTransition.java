package com.scott.xwidget.drawable;


import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface IDrawableEditTransition {
    IDrawableEditTransition setCorner(float corner);

    IDrawableEditTransition setCornerType(int cornerType);

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

    IDrawableEditTransition setBlurType(@BlurType int blurType);

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

    IDrawableEditTransition setBlurRadius(float r);

    IDrawableEditTransition setBlurColor(int color);

    IDrawableEditTransition setShadowRadius(float r);

    IDrawableEditTransition setShadowDx(int dx);

    IDrawableEditTransition setShadowDy(int dy);

    IDrawableEditTransition setShadowColor(int shadowColor);

    IDrawableEditTransition setSolidColor(int color);

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

    IDrawableEditTransition setSolidGradient(@GradientType int type);

    IDrawableEditTransition setGradientColors(int[] colors);


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

    IDrawableEditTransition setGradientOrientation(@Orientation int orientation);

    IDrawableEditTransition setGradientRadius(float r);

    IDrawableEditTransition setStrokeColor(int color);

    IDrawableEditTransition setStrokeBorder(float border);

    /**
     *  commit the drawable changes
     */
    void commit();
}