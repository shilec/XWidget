package com.scott.xwidget.parser;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.scott.xwidget.IWidgetParser;
import com.scott.xwidget.R;
import com.scott.xwidget.drawable.ShadowGradientDrawable;


public class XViewParserTemplate implements IWidgetParser {
    public XViewParserTemplate () {
    }

    @Nullable
    @Override
    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {
        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XTextViewCustom);
        int type = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_state, 0);

        Drawable drb = null;
        if (drawable instanceof GradientDrawable) {
            drb = parseGradientDrawable((GradientDrawable) drawable, arr);
        } else if (drawable instanceof StateListDrawable) {
            drb = parseStateListDrawable((StateListDrawable) drawable, arr, type);
        }

        if (drb == null) {
            if (type == 0) {
                drb = parseGradientDrawable(new ShadowGradientDrawable(), arr);
            } else if (type == 1) {
                drb = parseStateListDrawable(new StateListDrawable(), arr, type);
            }
        }

        arr.recycle();
        return drb;
    }

    private Drawable parseGradientDrawable(GradientDrawable gradientDrawable, TypedArray arr) {
        return buildNormalGradientDrawable(gradientDrawable, arr);
    }

    private GradientDrawable buildStatedGradientDrawable(GradientDrawable gradientDrawable, TypedArray arr) {
        int statedSoldColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_color, Color.TRANSPARENT);
        float statedCorner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner, 0F);
        float statedBorder = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_border, 0F);
        int statedBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_stroke_color, Color.TRANSPARENT);
        float statedGradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_radius, 0F);
        int statedStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_start_color, Color.TRANSPARENT);
        int statedEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_end_color, Color.TRANSPARENT);
        int statedGradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_solid_gradient, 0);
        int statedGradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_gradient_orientation, 0);
        GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
        switch (statedGradientOrientation) {
            case 1:
                gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 2:
                gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
        }

        if (statedSoldColor != Color.TRANSPARENT) {
            gradientDrawable.setColor(statedSoldColor);
        }
        if (statedCorner != 0) {
            int stateCornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_stated_corner_type, 0);
            if (stateCornerType == 0) {
                gradientDrawable.setCornerRadius(statedCorner);
            } else {
                float[] corners = new float[] {0, 0, 0, 0, 0, 0, 0, 0};
                if ((stateCornerType & 0x01) == 0x01) { // top-left
                    corners[0] = statedCorner;
                    corners[1] = statedCorner;
                }
                if ((stateCornerType & 0x08) == 0x8) { // top-right
                    corners[2] = statedCorner;
                    corners[3] = statedCorner;
                }
                if ((stateCornerType & 0x16) == 0x16) { // bottom-right
                    corners[6] = statedCorner;
                    corners[7] = statedCorner;
                }
                if ((stateCornerType & 0x32) == 0x32) { // bottom-left
                    corners[4] = statedCorner;
                    corners[5] = statedCorner;
                }
                gradientDrawable.setCornerRadii(corners);
            }
        }
        if (statedBorder != 0 && statedBorderColor != 0) {
            gradientDrawable.setStroke((int) statedBorder, statedBorderColor);
        }
        if (statedGradientRadius != 0) {
            gradientDrawable.setGradientRadius(statedGradientRadius);
        }
        if (statedStartColor != Color.TRANSPARENT && statedEndColor != Color.TRANSPARENT) {
            gradientDrawable.setColors(new int[] {statedStartColor, statedEndColor});
        }
        if (statedGradientType != 0) {
            gradientDrawable.setGradientType(statedGradientType);
        }
        if (gradientDrawable.getOrientation() != gradientOrientation) {
            gradientDrawable.setOrientation(gradientOrientation);
        }

        if (gradientDrawable instanceof ShadowGradientDrawable) {
            ShadowGradientDrawable shadowDrawable = (ShadowGradientDrawable) gradientDrawable;

            int shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_color, 0);
            float shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dx, 0f);
            float shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_dy, 0f);
            float shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stated_shadow_radius, 0f);
            shadowDrawable.setShadowColor(shadowColor);
            shadowDrawable.setCorner(statedCorner);
            shadowDrawable.setShadowDx(shadowDx);
            shadowDrawable.setShadowDy(shadowDy);
            shadowDrawable.setShadowR((int) shadowRadius);
        }

        return gradientDrawable;
    }

    private GradientDrawable buildNormalGradientDrawable(GradientDrawable gradientDrawable, TypedArray arr) {
        int statedSoldColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_solid_color, Color.TRANSPARENT);
        float statedCorner = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_corner, 0F);
        float statedBorder = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_stroke_border, 0F);
        int statedBorderColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_stroke_color, Color.TRANSPARENT);
        float statedGradientRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_gradient_radius, 0F);
        int statedStartColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_start_color, Color.TRANSPARENT);
        int statedEndColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_gradient_end_color, Color.TRANSPARENT);
        int statedGradientType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_solid_gradient, 0);
        int statedGradientOrientation = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_gradient_orientation, 0);
        GradientDrawable.Orientation gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
        switch (statedGradientOrientation) {
            case 1:
                gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 2:
                gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
        }

        int stateCornerType = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_corner_type, 0);
        if (stateCornerType == 0) {
            gradientDrawable.setCornerRadius(statedCorner);
        } else {
            float[] corners = new float[] {0, 0, 0, 0, 0, 0, 0, 0};
            if ((stateCornerType & 0x01) == 0x01) { // top-left
                corners[0] = statedCorner;
                corners[1] = statedCorner;
            }
            if ((stateCornerType & 0x08) == 0x8) { // top-right
                corners[2] = statedCorner;
                corners[3] = statedCorner;
            }
            if ((stateCornerType & 0x16) == 0x16) { // bottom-right
                corners[6] = statedCorner;
                corners[7] = statedCorner;
            }
            if ((stateCornerType & 0x32) == 0x32) { // bottom-left
                corners[4] = statedCorner;
                corners[5] = statedCorner;
            }
            gradientDrawable.setCornerRadii(corners);
         }
        gradientDrawable.setColor(statedSoldColor);
        gradientDrawable.setStroke((int) statedBorder, statedBorderColor);
        gradientDrawable.setGradientRadius(statedGradientRadius);
        if (statedStartColor != Color.TRANSPARENT && statedEndColor != Color.TRANSPARENT) {
            gradientDrawable.setColors(new int[] {statedStartColor, statedEndColor});
        }
        gradientDrawable.setGradientType(statedGradientType);
        gradientDrawable.setOrientation(gradientOrientation);

        if (gradientDrawable instanceof ShadowGradientDrawable) {
            ShadowGradientDrawable shadowDrawable = (ShadowGradientDrawable) gradientDrawable;

            int shadowColor = arr.getColor(R.styleable.XTextViewCustom_XTextViewCustom_shadow_color, 0);
            float shadowDx = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dx, 0f);
            float shadowDy = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_dy, 0f);
            float shadowRadius = arr.getDimension(R.styleable.XTextViewCustom_XTextViewCustom_shadow_radius, 0f);
            shadowDrawable.setShadowColor(shadowColor);
            shadowDrawable.setCorner(statedCorner);
            shadowDrawable.setShadowDx(shadowDx);
            shadowDrawable.setShadowDy(shadowDy);
            shadowDrawable.setShadowR((int) shadowRadius);
        }

        return gradientDrawable;
    }

    private Drawable parseStateListDrawable(StateListDrawable stateListDrawable, TypedArray arr, int stateType) {
        GradientDrawable normaDrawable = buildNormalGradientDrawable(new ShadowGradientDrawable(), arr);
        if (stateType == 0)
            return normaDrawable;

        int[] state_arr = null;
        int[] state_normal = null;


        if (stateType == 1) {
            state_arr = new int[] {android.R.attr.state_pressed};
            state_normal = new int[] {-android.R.attr.state_pressed};
        } else if (stateType == 2) {
            state_arr = new int[] {android.R.attr.state_selected};
            state_normal = new int[] {-android.R.attr.state_selected};
        } else if (stateType == 3) {
            state_arr = new int[] {android.R.attr.state_checked};
            state_normal = new int[] {-android.R.attr.state_checked};
        }

        GradientDrawable statedDrawable = buildStatedGradientDrawable(buildNormalGradientDrawable(new ShadowGradientDrawable(), arr), arr);
        stateListDrawable.addState(state_arr, statedDrawable);
        stateListDrawable.addState(state_normal, normaDrawable);
        return stateListDrawable;
    }
}