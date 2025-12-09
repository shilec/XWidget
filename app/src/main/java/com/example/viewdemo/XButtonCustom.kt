package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.scott.xwidget.annotation.XWidgetView
import com.scott.xwidget.injectXWidget

/**
 * 自定义Button，使用XWidget扩展函数简化初始化
 * 注解包名可省略，会自动检测
 */
@XWidgetView
class XButtonCustom(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        injectXWidget(attrs)
    }
}