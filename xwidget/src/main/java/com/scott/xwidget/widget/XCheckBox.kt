package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XCheckBox(context: Context, attrs: AttributeSet?) : AppCompatCheckBox(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}