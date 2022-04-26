package com.scott.xwidget;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.GradientDrawableDecorator;
import com.scott.xwidget.drawable.IDrawableEditor;
import com.scott.xwidget.drawable.RippleDrawableDecorator;
import com.scott.xwidget.drawable.StateListDrawableDecorator;
import com.scott.xwidget.drawable.XWidgetEditor;
import com.scott.xwidget.widget.decorator.IWidgetDecorator;
import com.scott.xwidget.widget.decorator.WidgetDecoratorFactory;

import java.util.HashMap;
import java.util.Map;

public class XWidgetParser {
    private static final Map<Class<? extends View>, IWidgetParser> DEFAULT_PARSERS = new HashMap<>();

    public static void addParser(Class<? extends View> vCls, IWidgetParser parser) {
        synchronized (DEFAULT_PARSERS) {
            DEFAULT_PARSERS.put(vCls, parser);
        }
    }

    public static <T extends View> void inject(T t, AttributeSet attrs, IWidgetParser customParser) {
        try {
            injectInternal(t, attrs, customParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T extends View> void injectInternal(T t, AttributeSet attrs, IWidgetParser customParser) throws ClassNotFoundException {
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
        if (drawable instanceof StateListDrawableDecorator
                && ((StateListDrawableDecorator) drawable).getStateType() == StateListDrawableDecorator.STATE_TYPE_PRESSED) {
            t.setClickable(true);
        }

        if (drawable instanceof RippleDrawableDecorator) {
            t.setClickable(true);
        }

        t.setBackground(drawable);

        decorateWidget(drawable, t);
    }

    private static <T extends View> void decorateWidget(Drawable drawable, T t) {
        IWidgetDecorator<T> decorator = (IWidgetDecorator<T>) WidgetDecoratorFactory.getWidgetDecorator(t.getClass());
        if (decorator != null) {

            Pair<DrawableInfo, DrawableInfo> drawableInfo = getNormalDrawableInfo(drawable);
            if (drawableInfo == null) {
                return;
            }

            DrawableInfo normalInfo = drawableInfo.first;
            DrawableInfo statedInfo = drawableInfo.second;

            decorator.decorate(t, normalInfo, statedInfo);
        }
    }

    private static Pair<DrawableInfo, DrawableInfo> getNormalDrawableInfo(Drawable drawable) {
        if (drawable == null) return null;

        DrawableInfo normal = null;
        DrawableInfo state = null;

        if (drawable instanceof IDrawableEditor) {
            normal = ((IDrawableEditor) drawable).getXDrawableInfo();
        } else if (drawable instanceof StateListDrawableDecorator) {
            Drawable normaDrawable = ((StateListDrawableDecorator) drawable).getNormalDrawable();
            if (normaDrawable instanceof IDrawableEditor) {
                normal = ((IDrawableEditor) normaDrawable).getXDrawableInfo();
            }

            Drawable stateDrawable = ((StateListDrawableDecorator) drawable).getStateDrawable();
            if (stateDrawable instanceof IDrawableEditor) {
                state = ((IDrawableEditor) stateDrawable).getXDrawableInfo();
            }
        }

        if (normal != null && state == null) {
            state = new DrawableInfo();
            state.merge(normal);
        }
        return new Pair<>(normal, state);
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

    public static <T extends View> void inject(T t, AttributeSet attrs) {
        inject(t, attrs, null);
    }

    public @Nullable static <T extends View> XWidgetEditor getWidgetEditor(@NonNull T v) {
        Drawable drawable = v.getBackground();
        if (drawable == null) return null;

        if (drawable instanceof GradientDrawableDecorator) {
            return new XWidgetEditor(((GradientDrawableDecorator) drawable).newBuilder(), null);
        }

        if (drawable instanceof StateListDrawableDecorator) {
            GradientDrawableDecorator normal = (GradientDrawableDecorator) ((StateListDrawableDecorator) drawable).getNormalDrawable();
            GradientDrawableDecorator state = (GradientDrawableDecorator) ((StateListDrawableDecorator) drawable).getStateDrawable();
            return new XWidgetEditor(normal.newBuilder(), state.newBuilder());
        }

        if (drawable instanceof RippleDrawableDecorator) {
            return new XWidgetEditor(((RippleDrawableDecorator) drawable).newBuilder(), ((RippleDrawableDecorator) drawable).newBuilder());
        }
        return null;
    }
}