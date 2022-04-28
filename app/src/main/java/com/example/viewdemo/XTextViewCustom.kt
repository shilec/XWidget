package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.XWidget
import com.scott.xwidget.annotation.XWidgetView

@XWidgetView("com.example.viewdemo")
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        XWidget.inject(this, attrs, null /*自定义解析器*/)
    }
}