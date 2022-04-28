package com.scott.xwidget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scott.xwidget.annotation.StateType;
import com.scott.xwidget.drawable.DrawableInfo;
import com.scott.xwidget.drawable.GradientDrawableDecorator;
import com.scott.xwidget.drawable.RippleDrawableDecorator;
import com.scott.xwidget.drawable.StateListDrawableDecorator;
import com.scott.xwidget.drawable.editor.IDrawableEditor;
import com.scott.xwidget.drawable.editor.IStateDrawableEditor;
import com.scott.xwidget.drawable.editor.RippleDrawableStateEditorWrapper;
import com.scott.xwidget.drawable.editor.StateDrawableWrapper;
import com.scott.xwidget.widget.decorator.IWidgetDecorator;
import com.scott.xwidget.widget.decorator.WidgetDecoratorFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XWidget {
    private static final Map<Class<? extends View>, IWidgetParser> DEFAULT_PARSERS = new ConcurrentHashMap<>();

    /**
     * 添加一个目标View的drawable解析器，该解析器用于将自定义属性实例化为一个合适的drawable
     * see {@link com.scott.xwidget.template.XViewParserTemplate} example
     * @param vCls 要解析的View class
     * @param parser 解析器
     */
    public static void addParser(Class<? extends View> vCls, IWidgetParser parser) {
        synchronized (DEFAULT_PARSERS) {
            DEFAULT_PARSERS.put(vCls, parser);
        }
    }

    /**
     * 将drawable解析器解析出的drawable注入到目标View {@link com.scott.xwidget.template.XViewTemplate}
     * 指定自定义的解析器，可用于拦截默认的解析器
     * @param t 目标View
     * @param attrs {@link AttributeSet}
     * @param customParser 自定义drawable解析器
     * @param <T> View
     */
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

        // 如果是有状态的且状态为按压，需要设置当前的目标可点击
        if (drawable instanceof StateListDrawableDecorator
                && ((StateListDrawableDecorator) drawable).getStateType() == StateType.TYPE_PRESSED) {
            t.setClickable(true);
        }

        // 如果是ripple则需要view可点击
        if (drawable instanceof RippleDrawableDecorator) {
            t.setClickable(true);
        }

        t.setBackground(drawable);

        decorateWidget(drawable, t);
    }

    /**
     * 用于装饰指定的组件，由于例如一些View自有属性。可以和state联动。
     * 例如：可以实现按压/松开控件的字体颜色变化，colorFilter变化
     * builtin: {@link com.scott.xwidget.widget.decorator.ImageViewWidgetDecorator}
     * {@link com.scott.xwidget.widget.decorator.TextViewWidgetDecorator}
     */
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
        } else if (drawable instanceof IStateDrawableEditor) {
            Drawable normaDrawable = ((IStateDrawableEditor) drawable).getNormalEditor().asDrawable();
            if (normaDrawable instanceof IDrawableEditor) {
                normal = ((IDrawableEditor) normaDrawable).getXDrawableInfo();
            }

            Drawable stateDrawable = ((IStateDrawableEditor) drawable).getStateEditor().asDrawable();
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

    /**
     * 注入目标View {@link com.scott.xwidget.template.XViewTemplate}
     * @param t 目标View
     * @param attrs {@link AttributeSet}
     * @param <T> View
     */
    public static <T extends View> void inject(T t, AttributeSet attrs) {
        inject(t, attrs, null);
    }

    /**
     * 从目标View中获取一个有状态的ripple Drawable编辑器
     * @param v 目标View
     * @param stateType 目标状态类型 {@link StateType}
     * @param <T> View
     * @return 可能返回null，返回结果
     */
    public @Nullable static <T extends View> IStateDrawableEditor getStateRippleDrawableEditor(@NonNull T v, @StateType int stateType) {
        if (v == null) return null;
        Drawable drawable = v.getBackground();

        assertSupportDrawable(v, drawable);

        if (drawable == null) {
            drawable = new RippleDrawableDecorator(ColorStateList.valueOf(0),
                    new StateListDrawableDecorator(stateType, new GradientDrawableDecorator(new DrawableInfo()),
                            new GradientDrawableDecorator(new DrawableInfo())), null, new DrawableInfo());
            v.setBackground(drawable);
        }

        if (drawable instanceof RippleDrawableDecorator) {
            return new RippleDrawableStateEditorWrapper((RippleDrawableDecorator) drawable);
        } else {
            return new RippleDrawableStateEditorWrapper(new RippleDrawableDecorator(ColorStateList.valueOf(0), drawable, null, new DrawableInfo()));
        }
    }

    /**
     * 从目标View中获取一个无状态的Drawable可设置ripple的编辑器
     * @param v 目标View
     * @param <T> View
     * @return 可能返回null，返回结果
     */
    public @Nullable static <T extends View> IDrawableEditor getNormalRippleDrawableEditor(@NonNull T v) {
        if (v == null) return null;
        Drawable drawable = v.getBackground();

        assertSupportDrawable(v, drawable);

        if (drawable == null) {
            drawable = new RippleDrawableDecorator(ColorStateList.valueOf(0),
                    new GradientDrawableDecorator(new DrawableInfo()), null, new DrawableInfo());
            v.setBackground(drawable);
        }

        if (drawable instanceof RippleDrawableDecorator) {
            return (IDrawableEditor) drawable;
        } else {
            return new RippleDrawableDecorator(ColorStateList.valueOf(0), drawable, null, new DrawableInfo());
        }
    }

    /**
     * 从目标View中获取一个无状态的Drawable编辑器
     * @param v 目标View
     * @param <T> View
     * @return 可能返回null，返回结果
     */
    public @Nullable
    static <T extends View> IDrawableEditor getNormalDrawableEditor(@NonNull T v) {
        if (v == null) return null;

        Drawable drawable = v.getBackground();

        assertSupportDrawable(v, drawable);

        if (drawable == null) {
            IDrawableEditor editor = new GradientDrawableDecorator(new DrawableInfo());
            v.setBackground(editor.asDrawable());
            return editor;
        }

        if (drawable instanceof GradientDrawableDecorator) {
            return (IDrawableEditor) drawable;
        }

        if (drawable instanceof RippleDrawableDecorator) {
            return (IDrawableEditor) drawable;
        }

        if (drawable instanceof StateListDrawableDecorator) {
            return ((StateListDrawableDecorator) drawable).getNormalEditor();
        }

        IDrawableEditor editor = new GradientDrawableDecorator(new DrawableInfo());
        v.setBackground(editor.asDrawable());
        return editor;
    }

    /**
     * 从目标View中获取一个有状态的Drawable编辑器
     * @param v 目标View
     * @param <T> View
     * @return 可能返回null，返回结果
     */
    public @Nullable
    static <T extends View> IStateDrawableEditor getStateDrawableEditor(@NonNull T v) {
        if (v == null) return null;

        Drawable drawable = v.getBackground();

        assertSupportDrawable(v, drawable);

        if (drawable instanceof StateListDrawableDecorator) {
            return getStateDrawableEditor(v, ((StateListDrawableDecorator) drawable).getStateType());
        }
        return getStateDrawableEditor(v, StateType.TYPE_NONE);
    }


    /**
     * 从目标View中获取一个有状态的Drawable编辑器
     * @param v 目标View
     * @param stateType 目标状态类型 {@link StateType}
     * @param <T> View
     * @return 可能返回null，返回结果
     */
    public @Nullable
    static <T extends View> IStateDrawableEditor getStateDrawableEditor(@NonNull T v, @StateType int stateType) {
        if (v == null) return null;

        Drawable drawable = v.getBackground();

        assertSupportDrawable(v, drawable);

        if (drawable == null) {
            IStateDrawableEditor editor = new StateDrawableWrapper(stateType, new GradientDrawableDecorator(new DrawableInfo()), new GradientDrawableDecorator(new DrawableInfo()));
            v.setBackground(editor.asDrawable());
            return editor;
        }

        if (drawable instanceof GradientDrawableDecorator) {
            return new StateDrawableWrapper(stateType, (GradientDrawableDecorator) drawable, (GradientDrawableDecorator) drawable);
        }

        if (drawable instanceof StateListDrawableDecorator) {
            return (IStateDrawableEditor) drawable;
        }

        if (drawable instanceof RippleDrawableDecorator) {
            return new RippleDrawableStateEditorWrapper((RippleDrawableDecorator) drawable);
        }

        IStateDrawableEditor editor = new StateDrawableWrapper(stateType, new GradientDrawableDecorator(new DrawableInfo()), new GradientDrawableDecorator(new DrawableInfo()));
        v.setBackground(editor.asDrawable());
        return editor;
    }

    /**
     * 异常提示
     * @param v 目标视图
     * @param drawable 目标drawable
     */
    private static void assertSupportDrawable(View v, Drawable drawable) {
        if (v == null || drawable == null) return;

        if (drawable instanceof GradientDrawableDecorator) {
            return;
        }

        if (drawable instanceof RippleDrawableDecorator
                || drawable instanceof StateListDrawableDecorator) {
            if (!v.isClickable()) {
                new Throwable("Clickable is false, " + (drawable instanceof RippleDrawableDecorator ? "ripple" : "selector") + " may be not work right!" ).printStackTrace();
                return;
            }
        }

        new Throwable("You need to clear background: " + drawable.getClass().getName() + " on the view: " + v.getClass().getName() + " when you want to use XWidget!").printStackTrace();
    }
}