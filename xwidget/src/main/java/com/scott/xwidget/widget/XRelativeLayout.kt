package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XRelativeLayout(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}