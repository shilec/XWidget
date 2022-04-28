package com.scott.xwidget.drawable.editor;


import androidx.annotation.Keep;

import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.IDrawableDecorator;

@Keep
public interface IDrawableEditor extends IDrawableDecorator {
    IDrawableEditor commit(DrawableInfo drawableInfo);

    IDrawableEditTransaction edit();

    DrawableInfo getXDrawableInfo();
}
