package com.scott.xwidget.widget
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.scott.xwidget")
class XButton(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        com.scott.xwidget.XWidget.inject(this, attrs)
    }
}