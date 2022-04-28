package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XRelativeLayout(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}