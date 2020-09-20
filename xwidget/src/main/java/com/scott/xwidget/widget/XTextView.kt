package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}