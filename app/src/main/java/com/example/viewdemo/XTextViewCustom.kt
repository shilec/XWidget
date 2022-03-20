package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget

@XWidget("com.example.viewdemo")
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs, null /*自定义解析器*/)
    }
}