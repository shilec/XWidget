package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XLinearLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}