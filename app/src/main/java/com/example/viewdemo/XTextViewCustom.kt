package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.annotation.XWidgetView
import com.scott.xwidget.injectXWidget

/**
 * 自定义TextView，使用XWidget扩展函数简化初始化
 * 注解包名可省略，会自动检测
 */
@XWidgetView
class XTextViewCustom(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        injectXWidget(attrs)
    }
}