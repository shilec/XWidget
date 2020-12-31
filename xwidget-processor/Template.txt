package com.scott.xwidget.parser;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.scott.xwidget.IWidgetParser;
import com.scott.xwidget.R;
import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.GradientDrawableDecorator;
import com.scott.xwidget.drawable.StateListDrawableDecorator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class XViewParserTemplate implements IWidgetParser {
    public XViewParserTemplate() {
    }

    @Nullable
    @Override
    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {
        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XTextViewCustom);

        int type = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_state, 0);

        // 如果不是selector, 并且 normalDrawable != null, 则显示normalDrawable
        Drawable normalDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_drawable);
        if (type == 0 && normalDrawable != null)
            return normalDrawable;

        Drawable stateDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_stated_drawable);
        // 如果是selector 并且 normal 和 state 都不为 null, 则返回 selector
        if (normalDrawable != null && stateDrawable != null) {
            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
        }

        DrawableInfo normalDrawableInfo = DrawableInfo.fromNormalTypeArray(arr);
        if (type == 0) {
            return new GradientDrawableDecorator(normalDrawableInfo);
        }

        // 默认所有属性copy自normal, 只修改差异化部分
        DrawableInfo stateDrawableInfo = DrawableInfo.fromStatedTypeArray(arr);
        stateDrawableInfo.merge(normalDrawableInfo);

        normalDrawable = new GradientDrawableDecorator(normalDrawableInfo);
        stateDrawable = new GradientDrawableDecorator(stateDrawableInfo);

        arr.recycle();
        return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);
    }
}