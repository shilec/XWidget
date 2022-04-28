package com.scott.xwidget.drawable;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scott.xwidget.annotation.StateType;
import com.scott.xwidget.drawable.editor.DrawableEditTransaction;
import com.scott.xwidget.drawable.editor.IDrawableEditTransaction;
import com.scott.xwidget.drawable.editor.IDrawableEditor;
import com.scott.xwidget.drawable.editor.IStateDrawableEditor;

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
        mEditor = new DrawableEditTransaction(drawableInfo, this);
        mContent = content;
        mDrawableInfo = drawableInfo;
    }

    private final DrawableEditTransaction mEditor;

    @Override
    public IDrawableEditor commit(DrawableInfo drawableInfo) {
        mutate();

        this.mDrawableInfo.merge(drawableInfo);

        if (mContent instanceof IDrawableEditor) {
            ((IDrawableEditor) mContent).commit(drawableInfo);
        } else if (mContent instanceof IStateDrawableEditor) {
            IStateDrawableEditor stateDrawableEditor = (IStateDrawableEditor) mContent;
            IDrawableEditor editor;
            if (drawableInfo.currentState == StateType.TYPE_NONE) {
                editor = stateDrawableEditor.getNormalEditor();
            } else {
                editor = stateDrawableEditor.getStateEditor();
            }
            editor.commit(drawableInfo);
        }

        DrawableWrapUtils.wrap(mDrawableInfo, this);

        int id = getId(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setDrawable(0, mContent);
        } else {
            setDrawableByLayerId(id, mContent);
        }
        return this;
    }

    @Override
    public IDrawableEditTransaction edit() {
        return mEditor;
    }

    @Override
    public DrawableInfo getXDrawableInfo() {
        return mDrawableInfo;
    }

    @NonNull
    @Override
    public Drawable asDrawable() {
        return this;
    }
}
