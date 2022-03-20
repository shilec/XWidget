package com.scott.xwidget.drawable;

import android.graphics.Color;

import androidx.annotation.ColorInt;

/***
 *  transition for edit the drawable
 */
public class DrawableEditBuilder implements IDrawableEditBuilder {
    private final DrawableInfo drawableInfo;
    private IDrawableEditor drawableEditor;

    public DrawableEditBuilder(DrawableInfo drawableInfo, IDrawableEditor editor) {
        this.drawableInfo = drawableInfo;
        this.drawableEditor = editor;
    }

    public DrawableEditBuilder() {
        this.drawableInfo = new DrawableInfo();
    }

    @Override
    public IDrawableEditBuilder setCorner(float corner) {
        this.drawableInfo.corner = corner;
        return this;
    }

    @Override
    public IDrawableEditBuilder setCornerType(@CornerType int cornerType) {
        this.drawableInfo.cornerType = cornerType;
        return this;
    }

    @Override
    public IDrawableEditBuilder setBlurType(@BlurType int blurType) {
        this.drawableInfo.blurType = blurType;
        return this;
    }

    @Override
    public IDrawableEditBuilder setBlurRadius(float r) {
        this.drawableInfo.blurRadius = r;
        return this;
    }

    @Override
    public IDrawableEditBuilder setBlurColor(@ColorInt int color) {
        this.drawableInfo.blurColor = color;
        return this;
    }

    @Override
    public IDrawableEditBuilder setShadowRadius(float r) {
        this.drawableInfo.shadowRadius = r;
        return this;
    }

    @Override
    public IDrawableEditBuilder setShadowDx(int dx) {
        this.drawableInfo.shadowDx = dx;
        return this;
    }

    @Override
    public IDrawableEditBuilder setShadowDy(int dy) {
        this.drawableInfo.shadowDy = dy;
        return this;
    }

    @Override
    public IDrawableEditBuilder setShadowColor(int shadowColor) {
        this.drawableInfo.shadowColor = shadowColor;
        return this;
    }

    @Override
    public IDrawableEditBuilder setSolidColor(int color) {
        this.drawableInfo.solidColor = color;
        return this;
    }

    @Override
    public IDrawableEditBuilder setSolidGradient(@GradientType int type) {
        this.drawableInfo.gradientType = type;
        return null;
    }

    @Override
    public IDrawableEditBuilder setGradientColors(@ColorInt int[] colors) {
        if (colors == null)
            return this;
        if (colors.length == 2) {
            this.drawableInfo.gradientStartColor = colors[0];
            this.drawableInfo.gradientEndColor = colors[1];
            this.drawableInfo.gradientCenterColor = Color.TRANSPARENT;
        } else if (colors.length == 3) {
            this.drawableInfo.gradientStartColor = colors[0];
            this.drawableInfo.gradientCenterColor = colors[1];
            this.drawableInfo.gradientEndColor = colors[2];
        }
        return this;
    }

    @Override
    public IDrawableEditBuilder setGradientOrientation(@Orientation int orientation) {
        this.drawableInfo.gradientOrientation = orientation;
        return this;
    }

    @Override
    public IDrawableEditBuilder setGradientRadius(float r) {
        this.drawableInfo.gradientRadius = r;
        return this;
    }

    @Override
    public IDrawableEditBuilder setStrokeBorderColor(int color) {
        this.drawableInfo.strokeBorderColor = color;
        return this;
    }

    @Override
    public IDrawableEditBuilder setStrokeBorder(float border) {
        this.drawableInfo.strokeBorderWith = border;
        return this;
    }

    @Override
    public IDrawableEditBuilder setStrokeGradientColors(int[] colors) {
        if (colors == null)
            return this;
        if (colors.length == 2) {
            this.drawableInfo.strokeGradientStartColor = colors[0];
            this.drawableInfo.strokeGradientMiddleColor = colors[1];
            this.drawableInfo.strokeGradientEndColor = Color.TRANSPARENT;
        } else if (colors.length == 3) {
            this.drawableInfo.strokeGradientStartColor = colors[0];
            this.drawableInfo.strokeGradientMiddleColor = colors[1];
            this.drawableInfo.strokeGradientEndColor = colors[2];
        }
        return this;
    }

    @Override
    public IDrawableEditBuilder setStrokeGradientOffset(float[] offset) {
        if (offset.length != 4) {
            throw new RuntimeException("Parameter required length 4!");
        }
        return this;
    }

    @Override
    public IDrawableEditBuilder setStrokeGradientType(int type) {
        drawableInfo.strokeGradientType = type;
        return this;
    }

    @Override
    public IDrawableEditBuilder setTextColor(int color) {
        drawableInfo.textColor = color;
        return this;
    }

    @Override
    public IDrawableEditBuilder setImageColorFilter(int color) {
        drawableInfo.colorFilter = color;
        return this;
    }

    @Override
    public IDrawableEditBuilder setRippleEnable(boolean enable) {
        drawableInfo.rippleEnable = enable;
        return this;
    }

    @Override
    public IDrawableEditBuilder setRippleColor(int color) {
        drawableInfo.rippleColor = color;
        return this;
    }

    public void commit() {
        if (drawableEditor != null) {
            drawableEditor.commit(this.drawableInfo);
        }
    }
}
