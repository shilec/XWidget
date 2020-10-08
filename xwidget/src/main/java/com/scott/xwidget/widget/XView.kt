package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}