package com.scott.xwidget.widget.decorator

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.scott.xwidget.widget.XImageView

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/6/22 2:57 PM
 * @Description:
 */
object WidgetDecoratorFactory {
    private val decorators = HashMap<Class<*>, IWidgetDecorator<*>>()

    init {
        addWidgetDecorator(TextView::class.java, TextViewWidgetDecorator())
        addWidgetDecorator(AppCompatTextView::class.java, TextViewWidgetDecorator())
        addWidgetDecorator(XImageView::class.java, ImageViewWidgetDecorator())
    }

    @Suppress("UNCHECKED_CAST")
    fun <V: View> getWidgetDecorator(cls: Class<V>): IWidgetDecorator<V>? {
        val decorator = decorators[cls]
        if (decorator is IWidgetDecorator) {
            return decorator as IWidgetDecorator<V>?
        }

        for (c in decorators.keys) {
            var cp = cls.superclass
            while (cp != null) {
                if (c == cp) {
                    return decorators[c] as IWidgetDecorator<V>?
                }
                cp = cp.superclass
            }
        }
        return null
    }

    fun <V: View> addWidgetDecorator(cls: Class<V>, decorator: IWidgetDecorator<V>) {
        decorators[cls] = decorator
    }
}