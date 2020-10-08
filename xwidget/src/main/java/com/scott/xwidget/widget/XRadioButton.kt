package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XRadioButton(context: Context, attrs: AttributeSet?) : AppCompatRadioButton(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}