package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XFrameLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}