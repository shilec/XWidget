package com.scott.xwidget.drawable.editor;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import com.scott.xwidget.drawable.DrawableInfo;

/***
 *  transition for edit the drawable
 */
public class DrawableEditTransaction implements IDrawableEditTransaction {
    private final DrawableInfo drawableInfo;
    private IDrawableEditor drawableEditor;
    private IDrawableEditTransaction editTransaction;

    public DrawableEditTransaction(DrawableInfo drawableInfo, IDrawableEditor editor) {
        this.drawableInfo = drawableInfo;
        this.drawableEditor = editor;
    }

    @Override
    public IDrawableEditTransaction asProxy(IDrawableEditTransaction transaction) {
        this.editTransaction = transaction;
        return this;
    }

    public DrawableEditTransaction() {
        this.drawableInfo = new DrawableInfo();
    }

    @Override
    public IDrawableEditTransaction setCorner(float corner) {
        this.drawableInfo.corner = corner;
        if (editTransaction != null) {
            editTransaction.setCorner(corner);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setCornerType(@CornerType int cornerType) {
        this.drawableInfo.cornerType = cornerType;
        if (editTransaction != null) {
            editTransaction.setCornerType(cornerType);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setBlurType(@BlurType int blurType) {
        this.drawableInfo.blurType = blurType;
        if (editTransaction != null) {
            editTransaction.setBlurType(blurType);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setBlurRadius(float r) {
        this.drawableInfo.blurRadius = r;
        if (editTransaction != null) {
            editTransaction.setBlurRadius(r);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setBlurColor(@ColorInt int color) {
        this.drawableInfo.blurColor = color;
        if (editTransaction != null) {
            editTransaction.setBlurColor(color);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setShadowRadius(float r) {
        this.drawableInfo.shadowRadius = r;
        if (editTransaction != null) {
            editTransaction.setShadowRadius(r);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setShadowDx(int dx) {
        this.drawableInfo.shadowDx = dx;
        if (editTransaction != null) {
            editTransaction.setShadowDx(dx);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setShadowDy(int dy) {
        this.drawableInfo.shadowDy = dy;
        if (editTransaction != null) {
            editTransaction.setShadowDy(dy);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setShadowColor(int shadowColor) {
        this.drawableInfo.shadowColor = shadowColor;
        if (editTransaction != null) {
            editTransaction.setShadowColor(shadowColor);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setSolidColor(int color) {
        this.drawableInfo.solidColor = color;
        if (editTransaction != null) {
            editTransaction.setSolidColor(color);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setSolidGradient(@GradientType int type) {
        this.drawableInfo.gradientType = type;
        if (editTransaction != null) {
            editTransaction.setSolidGradient(type);
        }
        return null;
    }

    @Override
    public IDrawableEditTransaction setGradientColors(@ColorInt int[] colors) {
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

        if (editTransaction != null) {
            editTransaction.setGradientColors(colors);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setGradientOrientation(@Orientation int orientation) {
        this.drawableInfo.gradientOrientation = orientation;

        if (editTransaction != null) {
            editTransaction.setGradientOrientation(orientation);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setGradientRadius(float r) {
        this.drawableInfo.gradientRadius = r;
        if (editTransaction != null) {
            editTransaction.setGradientRadius(r);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setStrokeBorderColor(int color) {
        this.drawableInfo.strokeBorderColor = color;
        if (editTransaction != null) {
            editTransaction.setStrokeBorderColor(color);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setStrokeBorder(float border) {
        this.drawableInfo.strokeBorderWith = border;
        if (editTransaction != null) {
            editTransaction.setStrokeBorder(border);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setStrokeGradientColors(int[] colors) {
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

        if (editTransaction != null) {
            editTransaction.setStrokeGradientColors(colors);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setStrokeGradientOffset(float[] offset) {
        if (offset.length != 4) {
            throw new RuntimeException("Parameter required length 4!");
        }

        drawableInfo.strokeGradientOffsetX = offset[0];
        drawableInfo.strokeGradientOffsetY = offset[1];
        drawableInfo.strokeGradientOffsetX1 = offset[2];
        drawableInfo.strokeGradientOffsetY1 = offset[3];

        if (editTransaction != null) {
            editTransaction.setStrokeGradientOffset(offset);
        }

        return this;
    }

    @Override
    public IDrawableEditTransaction setStrokeGradientType(int type) {
        drawableInfo.strokeGradientType = type;

        if (editTransaction != null) {
            editTransaction.setStrokeGradientType(type);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setTextColor(int color) {
        drawableInfo.textColor = color;

        if (editTransaction != null) {
            editTransaction.setTextColor(color);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setImageColorFilter(int color) {
        drawableInfo.colorFilter = color;

        if (editTransaction != null) {
            editTransaction.setImageColorFilter(color);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setRippleEnable(boolean enable) {
        drawableInfo.rippleEnable = enable;

        if (editTransaction != null) {
            editTransaction.setRippleEnable(enable);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction setRippleColor(int color) {
        drawableInfo.rippleColor = color;

        if (editTransaction != null) {
            editTransaction.setRippleColor(color);
        }
        return this;
    }

    public IDrawableEditor commit() {

        if (drawableEditor != null) {
            drawableEditor.commit(this.drawableInfo);
        }

        if (editTransaction != null) {
            editTransaction.commit();
        }
        return null;
    }
}
