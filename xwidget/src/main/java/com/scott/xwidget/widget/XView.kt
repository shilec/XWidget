package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}