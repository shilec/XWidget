package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}