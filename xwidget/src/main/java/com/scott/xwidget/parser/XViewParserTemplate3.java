//package com.scott.xwidget.parser;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.GradientDrawable;
//import android.graphics.drawable.StateListDrawable;
//import android.util.AttributeSet;
//
//import androidx.annotation.DrawableRes;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import com.scott.xwidget.IWidgetParser;
//import com.scott.xwidget.R;
//import com.scott.xwidget.drawable.BlurDrawableRender;
//import com.scott.xwidget.drawable.GradientDrawableDecorator;
//import com.scott.xwidget.drawable.ShadowDrawableRender;
//import com.scott.xwidget.utils.ParseUtils;
//
//
//public class XViewParserTemplate3 implements IWidgetParser {
//    public XViewParserTemplate3() {
//    }
//
//    @Nullable
//    @Override
//    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {
//        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XTextViewCustom);
//        // 是否是selector
//        int type = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_state, 0);
//
//        // 是否使用已有drawable
//        Drawable drb = null;
//        if (drawable instanceof GradientDrawable) {
//            drb = parseGradientDrawable((GradientDrawable) drawable, arr);
//        } else if (drawable instanceof StateListDrawable) {
//            drb = parseStateListDrawable((StateListDrawable) drawable, arr, type);
//        }
//
//        if (drb == null) {
//            // 非selector类型
//            if (type == 0) {
//                drb = parseGradientDrawable(new GradientDrawableDecorator(), arr);
//            } else {
//                drb = parseStateListDrawable(new StateListDrawable(), arr, type);
//            }
//        }
//
//        arr.recycle();
//        return drb;
//    }
//
//    private Drawable parseGradientDrawable(GradientDrawable gradientDrawable, TypedArray arr) {
//        return buildNormalGradientDrawable(gradientDrawable, arr);
//    }
//
//    private GradientDrawable buildStatedGradientDrawable(GradientDrawableDecorator gradientDrawable, TypedArray arr) {
//        // 触发态下的填充颜色
//        int statedSoldColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_color, Color.TRANSPARENT);
//        if (statedSoldColor == Color.TRANSPARENT) {
//            statedSoldColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_solid_color, Color.TRANSPARENT);
//        }
//        float statedCorner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner, 0F);
//        if (statedCorner == 0) {
//            statedCorner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_corner, 0F);
//        }
//        float statedBorder = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_border, 0F);
//        if (statedBorder == 0) {
//            statedBorder = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stroke_border, 0F);
//        }
//        int statedBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_color, Color.TRANSPARENT);
//        if (statedBorderColor != Color.TRANSPARENT) {
//            statedBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stroke_color, Color.TRANSPARENT);
//        }
//        float statedGradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_radius, 0F);
//        int statedStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_start_color, Color.TRANSPARENT);
//        int statedCenterColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_center_color, Color.TRANSPARENT);
//        int statedEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_end_color, Color.TRANSPARENT);
//        int statedGradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_gradient, 0);
//        int statedGradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_orientation, 0);
//        GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
//        switch (statedGradientOrientation) {
//            case 1:
//                gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
//                break;
//            case 2:
//                gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
//                break;
//        }
//
//        if (statedSoldColor != Color.TRANSPARENT) {
//            gradientDrawable.setColor(statedSoldColor);
//        }
//
//        int stateCornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner_type, 0);
//        if (statedCorner != 0) {
//            if (stateCornerType == 0) {
//                gradientDrawable.setCornerRadius(statedCorner);
//            } else {
//                gradientDrawable.setCornerRadii(ParseUtils.INSTANCE.getCornersByType(stateCornerType, statedCorner));
//            }
//        }
//        if (statedBorder != 0 && statedBorderColor != 0) {
//            gradientDrawable.setStroke((int) statedBorder, statedBorderColor);
//        }
//        if (statedGradientRadius != 0) {
//            gradientDrawable.setGradientRadius(statedGradientRadius);
//        }
//        if (statedStartColor != Color.TRANSPARENT && statedEndColor != Color.TRANSPARENT) {
//            // 如果只设置了渐变开始和渐变结束，则不使用渐变中心色值
//            if (statedCenterColor == Color.TRANSPARENT) {
//                gradientDrawable.setColors(new int[]{statedStartColor, statedEndColor});
//            } else {
//                gradientDrawable.setColors(new int[]{statedStartColor, statedCenterColor, statedEndColor});
//            }
//        }
//        if (statedGradientType != 0) {
//            gradientDrawable.setGradientType(statedGradientType);
//        }
//        if (gradientDrawable.getOrientation() != gradientOrientation) {
//            gradientDrawable.setOrientation(gradientOrientation);
//        }
//
//        float shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_radius, 0f);
//        int shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_color, 0);
//        float shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dx, 0f);
//        float shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dx, 0f);
//        if (shadowRadius == 0) {
//            shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_radius, 0);
//        }
//        if (shadowColor == 0) {
//            shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_shadow_color, 0);
//        }
//        if (shadowDx == 0) {
//            shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dx, 0f);
//        }
//        if (shadowDy == 0) {
//            shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dy, 0f);
//        }
//        if (shadowRadius != 0) {
//            gradientDrawable.addRender(new ShadowDrawableRender(shadowColor, statedCorner, (int) shadowRadius, shadowDx, shadowDy, stateCornerType, statedSoldColor));
//        }
//
//        // 模糊效果
//        // 模糊半径
//        float blurRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_radius, 0f);
//        if (blurRadius == 0) {
//            blurRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_blur_radius, 0f);
//        }
//        // 模糊颜色
//        int blurColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_color, 0);
//        if (blurColor == 0) {
//            blurColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_blur_color, 0);
//        }
//        // 模糊类型
//        int blurType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_blur_type, 0);
//        if (blurType == 0) {
//            blurType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_blur_type, 0);
//        }
//        if (blurRadius != 0) {
//            gradientDrawable.addRender(new BlurDrawableRender(blurColor, blurType, statedCorner, (int) blurRadius, stateCornerType));
//        }
//
//        return gradientDrawable;
//    }
//
//    private GradientDrawable buildNormalGradientDrawable(GradientDrawable gradientDrawable, TypedArray arr) {
//        // 填充色
//        int statedSoldColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_solid_color, Color.TRANSPARENT);
//        // 角度
//        float statedCorner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_corner, 0F);
//        // 描边的宽度
//        float statedBorder = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stroke_border, 0F);
//        // 描边颜色
//        int statedBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stroke_color, Color.TRANSPARENT);
//        // 渐变填充的角度
//        float statedGradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_gradient_radius, 0F);
//        // 渐变开始颜色
//        int statedStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_start_color, Color.TRANSPARENT);
//        int statedCenterColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_center_color, Color.TRANSPARENT);
//        int statedEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_end_color, Color.TRANSPARENT);
//        int statedGradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_solid_gradient, 0);
//        int statedGradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_gradient_orientation, 0);
//        GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
//        switch (statedGradientOrientation) {
//            case 1:
//                gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
//                break;
//            case 2:
//                gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
//                break;
//        }
//
//        int stateCornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_corner_type, 0);
//        if (stateCornerType == 0) {
//            gradientDrawable.setCornerRadius(statedCorner);
//        } else {
//            gradientDrawable.setCornerRadii(ParseUtils.INSTANCE.getCornersByType(stateCornerType, statedCorner));
//         }
//        gradientDrawable.setColor(statedSoldColor);
//        gradientDrawable.setStroke((int) statedBorder, statedBorderColor);
//        gradientDrawable.setGradientRadius(statedGradientRadius);
//        // 如果只设置了渐变开始和渐变结束，则不使用渐变中心色值
//        if (statedStartColor != Color.TRANSPARENT && statedEndColor != Color.TRANSPARENT) {
//            if (statedCenterColor == Color.TRANSPARENT) {
//                gradientDrawable.setColors(new int[]{statedStartColor, statedEndColor});
//            } else {
//                gradientDrawable.setColors(new int[]{statedStartColor, statedCenterColor, statedEndColor});
//            }
//        }
//        gradientDrawable.setGradientType(statedGradientType);
//        gradientDrawable.setOrientation(gradientOrientation);
//
//        if (gradientDrawable instanceof GradientDrawableDecorator) {
//            GradientDrawableDecorator decorator = (GradientDrawableDecorator) gradientDrawable;
//            float shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_radius, 0f);
//            // 渲染阴影
//            if (shadowRadius != 0) {
//                int shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_shadow_color, 0);
//                float shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dx, 0f);
//                float shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dy, 0f);
//                decorator.addRender(new ShadowDrawableRender(shadowColor, statedCorner, (int) shadowRadius, shadowDx, shadowDy, stateCornerType, statedSoldColor));
//            }
//
//            // 渲染模糊
//            float blurRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_blur_radius, 0f);
//            if (blurRadius != 0) {
//                int blurColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_blur_color, 0);
//                int blurType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_blur_type, 0);
//                decorator.addRender(new BlurDrawableRender(blurColor, blurType, statedCorner, (int) blurRadius, stateCornerType));
//            }
//        }
//
//        return gradientDrawable;
//    }
//
//    private Drawable parseStateListDrawable(StateListDrawable stateListDrawable, TypedArray arr, int stateType) {
//        GradientDrawable normaDrawable = buildNormalGradientDrawable(new GradientDrawableDecorator(), arr);
//        // 如果不是selector, 直接返回
//        if (stateType == 0)
//            return normaDrawable;
//
//        int[] state_arr = null;
//        int[] state_normal = null;
//
//        if (stateType == 1) {
//            state_arr = new int[] {android.R.attr.state_pressed};
//            state_normal = new int[] {-android.R.attr.state_pressed};
//        } else if (stateType == 2) {
//            state_arr = new int[] {android.R.attr.state_selected};
//            state_normal = new int[] {-android.R.attr.state_selected};
//        } else if (stateType == 3) {
//            state_arr = new int[] {android.R.attr.state_checked};
//            state_normal = new int[] {-android.R.attr.state_checked};
//        }
//
//        // 如果指定了俩个drawable 用于切换，直接使用
//        Drawable customDrawableDefault = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_drawable);
//        Drawable customStateDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_stated_drawable);
//        if (customDrawableDefault != null && customStateDrawable != null) {
//            stateListDrawable.addState(state_arr, customStateDrawable);
//            stateListDrawable.addState(state_normal, customDrawableDefault);
//            return stateListDrawable;
//        }
//
//        // 从默认的drawable继承属性或者扩展
//        GradientDrawable statedDrawable = buildStatedGradientDrawable((GradientDrawableDecorator) buildNormalGradientDrawable(new GradientDrawableDecorator(), arr), arr);
//        stateListDrawable.addState(state_arr, statedDrawable);
//        stateListDrawable.addState(state_normal, normaDrawable);
//        return stateListDrawable;
//    }
//}