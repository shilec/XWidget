package com.scott.xwidget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.scott.xwidget.drawable.DrawableInfo

interface IWidgetParser {
    fun parseDrawable(context: Context, attrs: AttributeSet?, drawable: Drawable?): Drawable?

    fun getNormalDrawableInfo(): DrawableInfo

    fun getStatedDrawableInfo(): DrawableInfo
}