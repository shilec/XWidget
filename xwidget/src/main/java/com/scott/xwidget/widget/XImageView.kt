package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }

    var onDrawableStateChanged: ((IntArray) -> Unit)? = null

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        onDrawableStateChanged?.invoke(drawableState)
    }
}