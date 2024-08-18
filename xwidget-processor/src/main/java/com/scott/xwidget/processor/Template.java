package com.scott.xwidget.processor;


public class Template {

    public static final StringBuilder template = new StringBuilder()
            .append("package com.scott.xwidget.template;\n" +
                    "\n" +
                    "import android.content.Context;\n" +
                    "import android.content.res.ColorStateList;\n" +
                    "import android.content.res.TypedArray;\n" +
                    "import android.graphics.Color;\n" +
                    "import android.graphics.drawable.Drawable;\n" +
                    "import android.graphics.drawable.RippleDrawable;\n" +
                    "import android.util.AttributeSet;\n" +
                    "\n" +
                    "import com.scott.xwidget.IWidgetParser;\n" +
                    "import com.scott.xwidget.R;\n" +
                    "import com.scott.xwidget.drawable.DrawableInfo;\n" +
                    "import com.scott.xwidget.drawable.GradientDrawableDecorator;\n" +
                    "import com.scott.xwidget.drawable.RippleDrawableDecorator;\n" +
                    "import com.scott.xwidget.drawable.StateListDrawableDecorator;\n" +
                    "\n" +
                    "import org.jetbrains.annotations.NotNull;\n" +
                    "import org.jetbrains.annotations.Nullable;\n" +
                    "\n" +
                    "public class XViewParserTemplate implements IWidgetParser {\n" +
                    "\n" +
                    "    public XViewParserTemplate() {\n" +
                    "    }\n" +
                    "\n" +
                    "    @Nullable\n" +
                    "    @Override\n" +
                    "    public Drawable parseDrawable(@NotNull Context context, @Nullable AttributeSet attrs, @Nullable Drawable drawable) {\n" +
                    "        TypedArray arr = context.getResources().obtainAttributes(attrs, R.styleable.XViewTemplate);\n" +
                    "        TypedArray styleArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.XViewTemplate, 0, 0);\n" +
                    "\n" +
                    "        int type = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_state, 0);\n" +
                    "\n" +
                    "        Drawable normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_drawable);\n" +
                    "        if (type == 0 && normalDrawable != null) {\n" +
                    "            recycleTypedArrays(arr, styleArray);\n" +
                    "            return normalDrawable;\n" +
                    "        }\n" +
                    "\n" +
                    "        Drawable stateDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_drawable);\n" +
                    "        if (normalDrawable != null && stateDrawable != null) {\n" +
                    "            recycleTypedArrays(arr, styleArray);\n" +
                    "            return new StateListDrawableDecorator(type, normalDrawable, stateDrawable);\n" +
                    "        }\n" +
                    "\n" +
                    "        boolean rippleEnable = getBooleanAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_ripple_enable, false);\n" +
                    "        int rippleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_ripple_color, 0);\n" +
                    "\n" +
                    "        DrawableInfo mNormal = fromNormalTypeArray(arr, styleArray);\n" +
                    "        mNormal.state = type;\n" +
                    "        mNormal.rippleEnable = rippleEnable;\n" +
                    "        mNormal.rippleColor = rippleColor;\n" +
                    "\n" +
                    "        Drawable drawableResult;\n" +
                    "        if (type == 0) {\n" +
                    "            drawableResult = rippleEnable ? new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new GradientDrawableDecorator(mNormal), null, mNormal)\n" +
                    "                    : new GradientDrawableDecorator(mNormal);\n" +
                    "        } else {\n" +
                    "            DrawableInfo mStated = fromStatedTypeArray(arr, styleArray);\n" +
                    "            mStated.merge(mNormal);\n" +
                    "            mStated.state = type;\n" +
                    "\n" +
                    "            normalDrawable = new GradientDrawableDecorator(mNormal);\n" +
                    "            stateDrawable = new GradientDrawableDecorator(mStated);\n" +
                    "\n" +
                    "            drawableResult = rippleEnable ? new RippleDrawableDecorator(ColorStateList.valueOf(rippleColor), new StateListDrawableDecorator(type, normalDrawable, stateDrawable), null, mNormal)\n" +
                    "                    : new StateListDrawableDecorator(type, normalDrawable, stateDrawable);\n" +
                    "        }\n" +
                    "\n" +
                    "        recycleTypedArrays(arr, styleArray);\n" +
                    "        return drawableResult;\n" +
                    "    }\n" +
                    "\n" +
                    "    private int getIntAttribute(TypedArray arr, TypedArray styleArray, int index, int defaultValue) {\n" +
                    "        return arr.hasValue(index) ? arr.getInt(index, defaultValue) : styleArray.getInt(index, defaultValue);\n" +
                    "    }\n" +
                    "\n" +
                    "    private boolean getBooleanAttribute(TypedArray arr, TypedArray styleArray, int index, boolean defaultValue) {\n" +
                    "        return arr.hasValue(index) ? arr.getBoolean(index, defaultValue) : styleArray.getBoolean(index, defaultValue);\n" +
                    "    }\n" +
                    "\n" +
                    "    private int getColorAttribute(TypedArray arr, TypedArray styleArray, int index, int defaultValue) {\n" +
                    "        return arr.hasValue(index) ? arr.getColor(index, defaultValue) : styleArray.getColor(index, defaultValue);\n" +
                    "    }\n" +
                    "\n" +
                    "    private Drawable getDrawableAttribute(TypedArray arr, TypedArray styleArray, int index) {\n" +
                    "        return arr.hasValue(index) ? arr.getDrawable(index) : styleArray.getDrawable(index);\n" +
                    "    }\n" +
                    "\n" +
                    "    private void recycleTypedArrays(TypedArray arr, TypedArray styleArray) {\n" +
                    "        arr.recycle();\n" +
                    "        styleArray.recycle();\n" +
                    "    }\n" +
                    "\n" +
                    "    public DrawableInfo fromNormalTypeArray(TypedArray arr, TypedArray styleArray) {\n" +
                    "        DrawableInfo drawableInfo = new DrawableInfo();\n" +
                    "\n" +
                    "        drawableInfo.solidColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_solid_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.corner = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_corner, 0F);\n" +
                    "        drawableInfo.strokeBorderWith = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_border, 0F);\n" +
                    "        drawableInfo.strokeBorderColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_radius, 0F);\n" +
                    "        drawableInfo.gradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_start_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientCenterColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_center_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_end_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_solid_gradient, 0);\n" +
                    "        drawableInfo.gradientOrientation = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_gradient_orientation, 0);\n" +
                    "        drawableInfo.cornerType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_corner_type, 0);\n" +
                    "        drawableInfo.shadowRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_radius, 0f);\n" +
                    "        drawableInfo.shadowColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_color, 0);\n" +
                    "        drawableInfo.shadowDx = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_dx, 0f);\n" +
                    "        drawableInfo.shadowDy = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_shadow_dy, 0f);\n" +
                    "        drawableInfo.blurRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_radius, 0f);\n" +
                    "        drawableInfo.blurColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_color, 0);\n" +
                    "        drawableInfo.blurType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_blur_type, 0);\n" +
                    "        drawableInfo.normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_drawable);\n" +
                    "        drawableInfo.strokeGradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_start_color, 0);\n" +
                    "        drawableInfo.strokeGradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_end_color, 0);\n" +
                    "        drawableInfo.strokeGradientMiddleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_middle_color, 0);\n" +
                    "        drawableInfo.strokeGradientOffsetX = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetX1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_x1, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetY = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetY1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stroke_gradient_offset_y1, 0f);\n" +
                    "        drawableInfo.textColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_text_color, 0);\n" +
                    "        drawableInfo.colorFilter = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_color_filter, -1);\n" +
                    "\n" +
                    "        return drawableInfo;\n" +
                    "    }\n" +
                    "\n" +
                    "    public DrawableInfo fromStatedTypeArray(TypedArray arr, TypedArray styleArray) {\n" +
                    "        DrawableInfo drawableInfo = new DrawableInfo();\n" +
                    "\n" +
                    "        drawableInfo.solidColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_solid_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.corner = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_corner, 0F);\n" +
                    "        drawableInfo.strokeBorderWith = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_border, 0F);\n" +
                    "        drawableInfo.strokeBorderColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_radius, 0F);\n" +
                    "        drawableInfo.gradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_start_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientCenterColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_center_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_end_color, Color.TRANSPARENT);\n" +
                    "        drawableInfo.gradientType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_solid_gradient, 0);\n" +
                    "        drawableInfo.gradientOrientation = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_gradient_orientation, 0);\n" +
                    "        drawableInfo.cornerType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_corner_type, 0);\n" +
                    "        drawableInfo.shadowRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_radius, 0f);\n" +
                    "        drawableInfo.shadowColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_color, 0);\n" +
                    "        drawableInfo.shadowDx = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dx, 0f);\n" +
                    "        drawableInfo.shadowDy = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_shadow_dy, 0f);\n" +
                    "        drawableInfo.blurRadius = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_radius, 0f);\n" +
                    "        drawableInfo.blurColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_color, 0);\n" +
                    "        drawableInfo.blurType = getIntAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_blur_type, 0);\n" +
                    "        drawableInfo.normalDrawable = getDrawableAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_drawable);\n" +
                    "        drawableInfo.strokeGradientStartColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_start_color, 0);\n" +
                    "        drawableInfo.strokeGradientEndColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_end_color, 0);\n" +
                    "        drawableInfo.strokeGradientMiddleColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_middle_color, 0);\n" +
                    "        drawableInfo.strokeGradientOffsetX = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetX1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_x1, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetY = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y, 0f);\n" +
                    "        drawableInfo.strokeGradientOffsetY1 = getDimensionAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_stroke_gradient_offset_y1, 0f);\n" +
                    "        drawableInfo.textColor = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_text_color, 0);\n" +
                    "        drawableInfo.colorFilter = getColorAttribute(arr, styleArray, R.styleable.XViewTemplate_XViewTemplate_stated_color_filter, -1);\n" +
                    "\n" +
                    "        return drawableInfo;\n" +
                    "    }\n" +
                    "\n" +
                    "    private float getDimensionAttribute(TypedArray arr, TypedArray styleArray, int index, float defaultValue) {\n" +
                    "        return arr.hasValue(index) ? arr.getDimension(index, defaultValue) : styleArray.getDimension(index, defaultValue);\n" +
                    "    }\n" +
                    "}\n");

    // Add other template methods and properties if needed
}
