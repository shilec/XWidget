package com.scott.xwidget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scott.xwidget.drawable.GradientDrawableDecorator;
import com.scott.xwidget.drawable.StateListDrawableDecorator;
import com.scott.xwidget.drawable.WidgetEditorTransition;

import java.util.HashMap;
import java.util.Map;

public class XWidgetParser {
    private static final Map<Class<? extends View>, IWidgetParser> DEFAULT_PARSERS = new HashMap<>();

    public static void addParser(Class<? extends View> vCls, IWidgetParser parser) {
        synchronized (DEFAULT_PARSERS) {
            DEFAULT_PARSERS.put(vCls, parser);
        }
    }

    public static <T extends View> void inject(T t, AttributeSet attrs, IWidgetParser customParser) throws ClassNotFoundException {
        IWidgetParser parser = customParser;
        if (parser == null) {
            parser = DEFAULT_PARSERS.get(t.getClass());
        }

        if (parser == null) {
            parser = getDefaultParser(t.getClass().getSimpleName());
            if (parser != null) {
                DEFAULT_PARSERS.put(t.getClass(), parser);
            }
        }

        if (parser == null) {
            throw new ClassNotFoundException("Parser not found, did you declared public constructor with empty params?");
        }
        Drawable drawable = parser.parseDrawable(t.getContext(), attrs, t.getBackground());
        if (drawable instanceof StateListDrawable) {
            t.setClickable(true);
        }
        t.setBackground(drawable);
    }

    private static IWidgetParser getDefaultParser(String vName) {
        String fullParserName = "com.scott.xwidget.parser." + vName + "$$WidgetParser";
        try {
            Class<?> parserCls = Class.forName(fullParserName);
            Object o = parserCls.newInstance();
            return (IWidgetParser) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends View> void inject(T t, AttributeSet attrs) throws ClassNotFoundException {
        inject(t, attrs, null);
    }

    public @Nullable static <T extends View> WidgetEditorTransition getDrawableEditTransition(@NonNull T v) {
        Drawable drawable = v.getBackground();
        if (drawable == null) return null;

        if (drawable instanceof GradientDrawableDecorator) {
            return new WidgetEditorTransition(((GradientDrawableDecorator) drawable).beginTransition(), null);
        }

        if (drawable instanceof StateListDrawableDecorator) {
            GradientDrawableDecorator normal = (GradientDrawableDecorator) ((StateListDrawableDecorator) drawable).getNormalDrawable();
            GradientDrawableDecorator state = (GradientDrawableDecorator) ((StateListDrawableDecorator) drawable).getStateDrawable();
            return new WidgetEditorTransition(normal.beginTransition(), state.beginTransition());
        }
        return null;
    }
}