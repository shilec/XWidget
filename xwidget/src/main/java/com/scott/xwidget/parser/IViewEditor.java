package com.scott.xwidget.parser;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface IViewEditor {
    void setCorner(float corner);

    void setCornerType(@CornerType int cornerType);

    @IntDef({
            CornerType.ALL,
            CornerType.LEFT_BOTTOM,
            CornerType.LEFT_TOP,
            CornerType.RIGHT_TOP,
            CornerType.RIGHT_BOTTOM
    })
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @interface CornerType {
        int LEFT_TOP = 0X11;
        int RIGHT_TOP = 0X21;
        int LEFT_BOTTOM = 0X12;
        int RIGHT_BOTTOM = 0X22;
        int ALL = 0;
    }

    void setBlurType(@BlurType int blurType);

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

    void setNormalDrawable(Drawable drawable);

    void setNormalDrawable(@DrawableRes int drawableRes);

    void setStatedDrawable(Drawable statedDrawable);

    void setStatedDrawable(@DrawableRes int drawableRes);

    void setBlurRadius(float r);

    void setBlurColor(int color);

    void setBlurColor(Color blurColor);

    void setShadowRadius(float r);

    void setShadowDx(int dx);

    void setShadowDy(int dy);

    void setShadowColor(int shadowColor);

    void setSolidColor(int color);


}

