package com.scott.xwidget.drawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.scott.xwidget.drawable.render.BlurDrawableRender;
import com.scott.xwidget.drawable.render.GradientStrokeRender;
import com.scott.xwidget.drawable.render.ShadowDrawableRender;
import com.scott.xwidget.drawable.render.ShapedDrawableRender;
import com.scott.xwidget.utils.ParseUtils;

public class DrawableWrapUtils {

    public static void wrap(DrawableInfo drawableInfo, RippleDrawableDecorator drawableDecorator) {
        if (!drawableInfo.rippleEnable) {
            drawableDecorator.setColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            drawableDecorator.setColor(ColorStateList.valueOf(drawableInfo.rippleColor));
        }
    }

    public static void wrap(DrawableInfo drawableInfo, GradientDrawableDecorator drawable) {
        drawable.mutate();

        if (drawableInfo.shaped) {
            drawable.addRender(new ShapedDrawableRender(drawableInfo));
            return;
        }

        // 填充色
        if (drawableInfo.solidColor != Color.TRANSPARENT) {
            drawable.setColor(drawableInfo.solidColor);
        }

        // 圆角
        if (drawableInfo.corner != 0) {
            drawable.setCornerRadii(ParseUtils.INSTANCE.getCornersByType(drawableInfo.cornerType, drawableInfo.corner));
        }

        // 描边
        if (drawableInfo.strokeBorderWith != 0 && drawableInfo.strokeBorderColor != Color.TRANSPARENT) {
            drawable.setStroke((int) drawableInfo.strokeBorderWith, drawableInfo.strokeBorderColor);
        }

        // 渐变类型
        if (drawableInfo.gradientType != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (drawable.getGradientType() != drawableInfo.gradientType) {
                    drawable.setGradientType(drawableInfo.gradientType);
                }
            } else {
                drawable.setGradientType(drawableInfo.gradientType);
            }
        }

        // 渐变颜色
        if (drawableInfo.gradientStartColor != Color.TRANSPARENT &&
                drawableInfo.gradientEndColor != Color.TRANSPARENT) {
            if (drawableInfo.gradientCenterColor == Color.TRANSPARENT) {
                drawable.setColors(new int[] {drawableInfo.gradientStartColor, drawableInfo.gradientEndColor});
            } else {
                drawable.setColors(new int[] {drawableInfo.gradientStartColor, drawableInfo.gradientCenterColor, drawableInfo.gradientEndColor});
            }
        }

        // 渐变方向
        if (drawableInfo.gradientOrientation != 0) {
            drawable.setOrientation(DrawableInfo.getOrientation(drawableInfo.gradientOrientation));
        } else {
            drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        }

        // 渐变角度
        if (drawableInfo.gradientRadius != 0) {
            drawable.setGradientRadius(drawableInfo.gradientRadius);
        }

        // 渲染阴影
        if (drawableInfo.shadowRadius != 0) {
            drawable.addRender(new ShadowDrawableRender(drawableInfo.shadowColor,
                    drawableInfo.corner, (int) drawableInfo.shadowRadius, drawableInfo.shadowDx,
                    drawableInfo.shadowDy, drawableInfo.cornerType, drawableInfo.solidColor));
        }

        // 模糊渲染
        if (drawableInfo.blurRadius != 0) {
            drawable.addRender(new BlurDrawableRender(drawableInfo.blurColor, drawableInfo.blurType,
                    drawableInfo.corner, (int) drawableInfo.blurRadius, drawableInfo.cornerType));
        }

        // 渐变边框
        if (drawableInfo.strokeBorderWith != 0 && drawableInfo.strokeGradientStartColor != 0 && drawableInfo.strokeGradientEndColor != 0) {
            drawable.addRender(new GradientStrokeRender(drawableInfo));
        }


    }
}
