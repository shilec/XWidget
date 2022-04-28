package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XImageButton(context: Context, attrs: AttributeSet?) : AppCompatImageButton(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}