package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}