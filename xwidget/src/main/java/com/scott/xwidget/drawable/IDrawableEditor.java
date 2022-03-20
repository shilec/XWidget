package com.scott.xwidget.drawable;

public interface IDrawableEditor {
    void commit(DrawableInfo drawableInfo);

    IDrawableEditBuilder newBuilder();

    DrawableInfo getXDrawableInfo();
}
