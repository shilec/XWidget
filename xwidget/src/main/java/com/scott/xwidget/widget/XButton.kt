package com.scott.xwidget.widget
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.scott.xwidget")
class XButton(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}