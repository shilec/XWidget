package com.scott.xwidget

import android.util.AttributeSet
import android.view.View

/**
 * XWidget扩展函数，简化初始化流程
 * 使用方式：在自定义View的init块中调用 this.injectXWidget(attrs)
 */
inline fun <reified T : View> T.injectXWidget(attrs: AttributeSet?) {
    XWidget.inject(this, attrs)
}

/**
 * 使用自定义解析器注入
 */
inline fun <reified T : View> T.injectXWidget(attrs: AttributeSet?, parser: IWidgetParser?) {
    XWidget.inject(this, attrs, parser)
}
