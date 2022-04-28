package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XLinearLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}