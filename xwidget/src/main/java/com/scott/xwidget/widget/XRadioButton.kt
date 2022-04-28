package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XRadioButton(context: Context, attrs: AttributeSet?) : AppCompatRadioButton(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}