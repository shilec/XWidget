package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}