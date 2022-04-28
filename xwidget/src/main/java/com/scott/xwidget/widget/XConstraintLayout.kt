package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XConstraintLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}