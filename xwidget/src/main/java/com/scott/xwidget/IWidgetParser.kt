package com.scott.xwidget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.Keep

@Keep
interface IWidgetParser {
    fun parseDrawable(context: Context, attrs: AttributeSet?, drawable: Drawable?): Drawable?
}