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

        int type = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_state, 0);

        // 如果不是selector, 并且 normalDrawable != null, 则显示normalDrawable
        Drawable normalDrawable = arr.getDrawable(R.styleable.XViewTemplate_XViewTemplate_drawable);
        if (type == 0 && normalDrawable != null)
            return normalDrawable;

        Drawable stateDrawable = arr.getDrawable(R.styleable.XViewTemplate_XViewTemplate_stated_drawable);
        // 如果是selector 并且 normal 和 state 都不为 null, 则返回 selector
        if (normalDrawable != null && stateDrawable != null) {
            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
        }

        boolean rippleEnable = arr.getBoolean(R.styleable.XViewTemplate_XViewTemplate_ripple_enable, false);
        int rippleColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_ripple_color, 0);

        DrawableInfo mNormal = fromNormalTypeArray(arr);
        mNormal.state = type;
        mNormal.rippleEnable = rippleEnable;
        mNormal.rippleColor = rippleColor;

        if (type == 0) {
            if (rippleEnable) {
                return new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new GradientDrawableDecorator(mNormal), null, mNormal);
            } else {
                return new GradientDrawableDecorator(mNormal);
            }
        }

        // 默认所有属性copy自normal, 只修改差异化部分
        DrawableInfo mStated = fromStatedTypeArray(arr);
        mStated.merge(mNormal);
        mStated.state = type;

        normalDrawable = new GradientDrawableDecorator(mNormal);
        stateDrawable = new GradientDrawableDecorator(mStated);

        arr.recycle();
        if (!rippleEnable) {
            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
        } else {
            return new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new StateListDrawableDecorator(type, normalDrawable, stateDrawable), null, mNormal);
        }
    }

    public DrawableInfo fromNormalTypeArray(TypedArray arr) {
        DrawableInfo drawableInfo = new DrawableInfo();

        // 填充色
        drawableInfo.solidColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_solid_color, Color.TRANSPARENT);
        // 角度
        drawableInfo.corner = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_corner, 0F);
        // 描边的宽度
        drawableInfo.strokeBorderWith = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stroke_border, 0F);
        // 描边颜色
        drawableInfo.strokeBorderColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stroke_color, Color.TRANSPARENT);
        // 渐变填充的角度
        drawableInfo.gradientRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_gradient_radius, 0F);
        // 渐变开始颜色
        drawableInfo.gradientStartColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_solid_gradient, 0);
        drawableInfo.gradientOrientation = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_gradient_orientation, 0);
        drawableInfo.cornerType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_corner_type, 0);
        drawableInfo.shadowRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shadow_radius, 0f);

        drawableInfo.shadowColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_shadow_color, 0);
        drawableInfo.shadowDx = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shadow_dx, 0f);
        drawableInfo.shadowDy = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shadow_dy, 0f);

        drawableInfo.blurRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_blur_radius, 0f);
        drawableInfo.blurColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_blur_color, 0);
        drawableInfo.blurType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_blur_type, 0);

        drawableInfo.normalDrawable = arr.getDrawable(R.styleable.XViewTemplate_XViewTemplate_drawable);

        drawableInfo.strokeGradientStartColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_start_color, 0);
        drawableInfo.strokeGradientEndColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_end_color, 0);
        drawableInfo.strokeGradientMiddleColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_middle_color, 0);

        drawableInfo.strokeGradientOffsetX = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x, 0f);
        drawableInfo.strokeGradientOffsetX1 = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x1, 0f);
        drawableInfo.strokeGradientOffsetY = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y, 0f);
        drawableInfo.strokeGradientOffsetY1 = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y1, 0f);

        drawableInfo.textColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_text_color, 0);
        drawableInfo.colorFilter = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_color_filter, -1);

        drawableInfo.shaped = arr.getBoolean(R.styleable.XViewTemplate_XViewTemplate_shaped, false);
        drawableInfo.shapedTopLeftType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topLeftCornerType, 0);
        drawableInfo.shapedTopLeftSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topLeftSize, 0);

        drawableInfo.shapedTopRightType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topRightCornerType, 0);
        drawableInfo.shapedTopRightSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topRightSize, 0);

        drawableInfo.shapedBottomLeftType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomLeftCornerType, 0);
        drawableInfo.shapedBottomLeftSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomLeftSize, 0);

        drawableInfo.shapedBottomRightType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomRightCornerType, 0);
        drawableInfo.shapedBottomRightSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomRightSize, 0);

        drawableInfo.shapedLeftEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_leftEdgeType, 0);
        drawableInfo.shapedLeftEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_leftEdgeSize, 0);

        drawableInfo.shapedRightEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_rightEdgeType, 0);
        drawableInfo.shapedRightEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_rightEdgeSize, 0);

        drawableInfo.shapedTopEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topEdgeType, 0);
        drawableInfo.shapedTopEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topEdgeSize, 0);

        drawableInfo.shapedBottomEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomEdgeType, 0);
        drawableInfo.shapedBottomEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomEdgeSize, 0);
        return drawableInfo;
    }

    public DrawableInfo fromStatedTypeArray(TypedArray arr) {
        DrawableInfo drawableInfo = new DrawableInfo();

        // 填充色
        drawableInfo.solidColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_solid_color, Color.TRANSPARENT);
        // 角度
        drawableInfo.corner = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_corner, 0F);
        // 描边的宽度
        drawableInfo.strokeBorderWith = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_border, 0F);
        // 描边颜色
        drawableInfo.strokeBorderColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_color, Color.TRANSPARENT);
        // 渐变填充的角度
        drawableInfo.gradientRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_gradient_radius, 0F);
        // 渐变开始颜色
        drawableInfo.gradientStartColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_gradient_start_color, Color.TRANSPARENT);
        drawableInfo.gradientCenterColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_gradient_center_color, Color.TRANSPARENT);
        drawableInfo.gradientEndColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_gradient_end_color, Color.TRANSPARENT);
        drawableInfo.gradientType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_stated_solid_gradient, 0);
        drawableInfo.gradientOrientation = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_stated_gradient_orientation, 0);
        drawableInfo.cornerType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_stated_corner_type, 0);
        drawableInfo.shadowRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_shadow_radius, 0f);

        drawableInfo. shadowColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_shadow_color, 0);
        drawableInfo.shadowDx = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dx, 0f);
        drawableInfo.shadowDy = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dy, 0f);

        drawableInfo.blurRadius = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_blur_radius, 0f);
        drawableInfo.blurColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_blur_color, 0);
        drawableInfo.blurType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_stated_blur_type, 0);

        drawableInfo.normalDrawable = arr.getDrawable(R.styleable.XViewTemplate_XViewTemplate_stated_drawable);

        drawableInfo.strokeGradientStartColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_start_color, 0);
        drawableInfo.strokeGradientEndColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_end_color, 0);
        drawableInfo.strokeGradientMiddleColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_middle_color, 0);

        drawableInfo.strokeGradientOffsetX = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x, 0f);
        drawableInfo.strokeGradientOffsetX1 = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x1, 0f);
        drawableInfo.strokeGradientOffsetY = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y, 0f);
        drawableInfo.strokeGradientOffsetY1 = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y1, 0f);

        drawableInfo.textColor = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_text_color, 0);
        drawableInfo.colorFilter = arr.getColor(R.styleable.XViewTemplate_XViewTemplate_stated_color_filter, -1);

        drawableInfo.shaped = arr.getBoolean(R.styleable.XViewTemplate_XViewTemplate_shaped, false);
        drawableInfo.shapedTopLeftType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topLeftCornerType, 0);
        drawableInfo.shapedTopLeftSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topLeftSize, 0);

        drawableInfo.shapedTopRightType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topRightCornerType, 0);
        drawableInfo.shapedTopRightSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topRightSize, 0);

        drawableInfo.shapedBottomLeftType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomLeftCornerType, 0);
        drawableInfo.shapedBottomLeftSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomLeftSize, 0);

        drawableInfo.shapedBottomRightType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomRightCornerType, 0);
        drawableInfo.shapedBottomRightSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomRightSize, 0);

        drawableInfo.shapedLeftEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_leftEdgeType, 0);
        drawableInfo.shapedLeftEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_leftEdgeSize, 0);

        drawableInfo.shapedRightEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_rightEdgeType, 0);
        drawableInfo.shapedRightEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_rightEdgeSize, 0);

        drawableInfo.shapedTopEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_topEdgeType, 0);
        drawableInfo.shapedTopEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_topEdgeSize, 0);

        drawableInfo.shapedBottomEdgeType = arr.getInt(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomEdgeType, 0);
        drawableInfo.shapedBottomEdgeSize = arr.getDimension(R.styleable.XViewTemplate_XViewTemplate_shaped_bottomEdgeSize, 0);
        return drawableInfo;
    }
}