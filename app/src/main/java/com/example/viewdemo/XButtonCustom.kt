package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.scott.xwidget.XWidget
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.example.viewdemo")
class XButtonCustom(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        XWidget.inject(this, attrs)
    }
}