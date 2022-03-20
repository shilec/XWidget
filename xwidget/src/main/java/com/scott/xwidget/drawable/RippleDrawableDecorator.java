package com.scott.xwidget.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: shijiale
 * @Email: shilec@126.com
 * @Date: 2022/3/19 9:50 下午
 * @Description:
 */
public class RippleDrawableDecorator extends RippleDrawable implements IDrawableEditor {

    private final Drawable mContent;

    public Drawable getContent() {
        return mContent;
    }

    private final DrawableInfo mDrawableInfo;

    /**
     * Creates a new ripple drawable with the specified ripple color and
     * optional content and mask drawables.
     *
     * @param color   The ripple color
     * @param content The content drawable, may be {@code null}
     * @param mask    The mask drawable, may be {@code null}
     */
    public RippleDrawableDecorator(@NonNull ColorStateList color, @Nullable Drawable content, @Nullable Drawable mask, DrawableInfo drawableInfo) {
        super(color, content, mask);
        mEditor = new DrawableEditBuilder(drawableInfo, this);
        mContent = content;
        mDrawableInfo = drawableInfo;
    }

    private final DrawableEditBuilder mEditor;

    @Override
    public void commit(DrawableInfo drawableInfo) {
        mutate();
        DrawableWrapUtils.wrap(drawableInfo, this);
        int id = getId(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setDrawable(0, mContent);
        } else {
            setDrawableByLayerId(id, mContent);
        }
    }

    @Override
    public IDrawableEditBuilder newBuilder() {
        return mEditor;
    }

    @Override
    public DrawableInfo getXDrawableInfo() {
        return mDrawableInfo;
    }
}
