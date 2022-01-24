package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.annotation.XWidget

@XWidget("com.example.viewdemo")
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        background = XViewParserTemplateTest()
            .parseDrawable(context, attrs, null)
    }
}