package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.annotation.XWidget
import com.scott.xwidget.parser.XViewParserTemplate

@XWidget("com.example.viewdemo")
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        XWidgetParser.inject(this, attrs)
    }
}