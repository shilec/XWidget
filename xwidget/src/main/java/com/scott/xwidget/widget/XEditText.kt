package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}