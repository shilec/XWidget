package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XImageButton(context: Context, attrs: AttributeSet?) : AppCompatImageButton(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}