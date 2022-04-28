package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XFrameLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}