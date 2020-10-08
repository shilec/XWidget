package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XCheckBox(context: Context, attrs: AttributeSet?) : AppCompatCheckBox(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}