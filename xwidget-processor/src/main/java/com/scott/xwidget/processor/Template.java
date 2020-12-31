package com.scott.xwidget.processor;

class Template {
    public static StringBuilder template = new StringBuilder();
    static {
        template.append("package com.scott.xwidget.parser;\n");
        template.append("\n");
        template.append("import android.content.Context;\n");
        template.append("import android.content.res.TypedArray;\n");
        template.append("import android.graphics.drawable.Drawable;\n");
        template.append("import android.util.AttributeSet;\n");
        template.append("\n");
        template.append("import com.scott.xwidget.IWidgetParser;\n");
        template.append("import com.scott.xwidget.R;\n");
        template.append("import com.scott.xwidget.drawable.DrawableInfo;\n");
        template.append("import com.scott.xwidget.drawable.GradientDrawableDecorator;\n");
        template.append("import com.scott.xwidget.drawable.StateListDrawableDecorator;\n");
        template.append("\n");
        template.append("import org.jetbrains.annotations.NotNull;\n");
        template.append("import org.jetbrains.annotations.Nullable;\n");
        template.append("\n");
        template.append("\n");
        template.append("public class XViewParserTemplate implements IWidgetParser {\n");
        template.append("    public XViewParserTemplate() {\n");
        template.append("    }\n");
        template.append("\n");
        template.append("    @Nullable\n");
        template.append("    @Override\n");
        template.append("    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {\n");
        template.append("        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XTextViewCustom);\n");
        template.append("\n");
        template.append("        int type = arr.getInt(R.styleable.XTextViewCustom_XTextViewCustom_state, 0);\n");
        template.append("\n");
        template.append("        // 如果不是selector, 并且 normalDrawable != null, 则显示normalDrawable\n");
        template.append("        Drawable normalDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_drawable);\n");
        template.append("        if (type == 0 && normalDrawable != null)\n");
        template.append("            return normalDrawable;\n");
        template.append("\n");
        template.append("        Drawable stateDrawable = arr.getDrawable(R.styleable.XTextViewCustom_XTextViewCustom_stated_drawable);\n");
        template.append("        // 如果是selector 并且 normal 和 state 都不为 null, 则返回 selector\n");
        template.append("        if (normalDrawable != null && stateDrawable != null) {\n");
        template.append("            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);\n");
        template.append("        }\n");
        template.append("\n");
        template.append("        DrawableInfo normalDrawableInfo = DrawableInfo.fromNormalTypeArray(arr);\n");
        template.append("        if (type == 0) {\n");
        template.append("            return new GradientDrawableDecorator(normalDrawableInfo);\n");
        template.append("        }\n");
        template.append("\n");
        template.append("        // 默认所有属性copy自normal, 只修改差异化部分\n");
        template.append("        DrawableInfo stateDrawableInfo = DrawableInfo.fromStatedTypeArray(arr);\n");
        template.append("        stateDrawableInfo.merge(normalDrawableInfo);\n");
        template.append("\n");
        template.append("        normalDrawable = new GradientDrawableDecorator(normalDrawableInfo);\n");
        template.append("        stateDrawable = new GradientDrawableDecorator(stateDrawableInfo);\n");
        template.append("\n");
        template.append("        arr.recycle();\n");
        template.append("        return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);\n");
        template.append("    }\n");
        template.append("}\n");
    }
}
