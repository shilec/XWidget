package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.example.viewdemo")
class XButtonCustom(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}