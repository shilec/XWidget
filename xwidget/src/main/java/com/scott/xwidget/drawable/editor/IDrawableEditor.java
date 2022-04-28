package com.scott.xwidget.drawable.editor;


import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.IDrawableDecorator;

public interface IDrawableEditor extends IDrawableDecorator {
    IDrawableEditor commit(DrawableInfo drawableInfo);

    IDrawableEditTransaction edit();

    DrawableInfo getXDrawableInfo();
}
