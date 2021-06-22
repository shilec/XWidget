package com.scott.xwidget.widget.decorator

import android.view.View
import com.scott.xwidget.drawable.DrawableInfo

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/6/22 2:55 PM
 * @Description:
 */
interface IWidgetDecorator<V: View> {
    fun decorate(v: V, normalInfo: DrawableInfo, statedInfo: DrawableInfo)
}