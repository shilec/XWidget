package com.scott.xwidget.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XConstraintLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}