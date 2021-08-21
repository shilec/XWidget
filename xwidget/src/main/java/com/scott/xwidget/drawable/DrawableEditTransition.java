package com.scott.xwidget.drawable;

import android.graphics.Color;

import androidx.annotation.ColorInt;

/***
 *  transition for edit the drawable
 */
public class DrawableEditTransition implements IDrawableEditTransition {
    private final DrawableInfo drawableInfo;
    private IDrawableEditor drawableEditor;

    public DrawableEditTransition(DrawableInfo drawableInfo, IDrawableEditor editor) {
        this.drawableInfo = drawableInfo;
        this.drawableEditor = editor;
    }

    public DrawableEditTransition() {
        this.drawableInfo = new DrawableInfo();
    }

    @Override
    public IDrawableEditTransition setCorner(float corner) {
        this.drawableInfo.corner = corner;
        return this;
    }

    @Override
    public IDrawableEditTransition setCornerType(@CornerType int cornerType) {
        this.drawableInfo.cornerType = cornerType;
        return this;
    }

    @Override
    public IDrawableEditTransition setBlurType(@BlurType int blurType) {
        this.drawableInfo.blurType = blurType;
        return this;
    }

    @Override
    public IDrawableEditTransition setBlurRadius(float r) {
        this.drawableInfo.blurRadius = r;
        return this;
    }

    @Override
    public IDrawableEditTransition setBlurColor(@ColorInt int color) {
        this.drawableInfo.blurColor = color;
        return this;
    }

    @Override
    public IDrawableEditTransition setShadowRadius(float r) {
        this.drawableInfo.shadowRadius = r;
        return this;
    }

    @Override
    public IDrawableEditTransition setShadowDx(int dx) {
        this.drawableInfo.shadowDx = dx;
        return this;
    }

    @Override
    public IDrawableEditTransition setShadowDy(int dy) {
        this.drawableInfo.shadowDy = dy;
        return this;
    }

    @Override
    public IDrawableEditTransition setShadowColor(int shadowColor) {
        this.drawableInfo.shadowColor = shadowColor;
        return this;
    }

    @Override
    public IDrawableEditTransition setSolidColor(int color) {
        this.drawableInfo.solidColor = color;
        return this;
    }

    @Override
    public IDrawableEditTransition setSolidGradient(@GradientType int type) {
        this.drawableInfo.gradientType = type;
        return null;
    }

    @Override
    public IDrawableEditTransition setGradientColors(@ColorInt int[] colors) {
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
    public IDrawableEditTransition setGradientOrientation(@Orientation int orientation) {
        this.drawableInfo.gradientOrientation = orientation;
        return this;
    }

    @Override
    public IDrawableEditTransition setGradientRadius(float r) {
        this.drawableInfo.gradientRadius = r;
        return this;
    }

    @Override
    public IDrawableEditTransition setStrokeColor(int color) {
        this.drawableInfo.strokeBorderColor = color;
        return this;
    }

    @Override
    public IDrawableEditTransition setStrokeBorder(float border) {
        this.drawableInfo.strokeBorderWith = border;
        return this;
    }

    public void commit() {
        if (drawableEditor != null) {
            drawableEditor.commit(this.drawableInfo);
        }
    }
}
