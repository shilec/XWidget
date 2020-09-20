package com.example.viewdemo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.scott.xwidget.IWidgetParser
import com.scott.xwidget.parser.`XButton$$WidgetParser`

class XButtonParser2: `XButton$$WidgetParser`() {
    override fun parseDrawable(
        context: Context,
        attrs: AttributeSet?,
        drawable: Drawable?
    ): Drawable? {
        val ret = super.parseDrawable(context, attrs, drawable)
        if (ret is GradientDrawable) {
            ret.setColor(Color.GREEN)
        }
        return ret
    }
}