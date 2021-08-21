package com.scott.xwidget.widget.decorator

import android.content.res.ColorStateList
import android.widget.TextView
import com.scott.xwidget.Cons
import com.scott.xwidget.drawable.DrawableInfo

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/6/22 2:58 PM
 * @Description:
 */
open class TextViewWidgetDecorator<T: TextView> : IWidgetDecorator<T> {
    override fun decorate(v: T, normalInfo: DrawableInfo, statedInfo: DrawableInfo) {

        if (statedInfo.textColor == 0 && normalInfo.textColor == 0 || normalInfo.state == 0)
            return

        var stateArr: IntArray? = null
        var stateNormal: IntArray? = null

        when (normalInfo.state) {
            Cons.DrawableState.PRESSED -> {
                stateArr = intArrayOf(android.R.attr.state_pressed)
                stateNormal = intArrayOf(-android.R.attr.state_pressed)
            }
            Cons.DrawableState.SELECTED -> {
                stateArr = intArrayOf(android.R.attr.state_selected)
                stateNormal = intArrayOf(-android.R.attr.state_selected)
            }
            Cons.DrawableState.CHECKED -> {
                stateArr = intArrayOf(android.R.attr.state_checked)
                stateNormal = intArrayOf(-android.R.attr.state_checked)
            }
        }

        val states = arrayOf(stateArr, stateNormal)

        val stateColor = if (statedInfo.textColor == 0) v.currentTextColor else statedInfo.textColor
        val normalColor = if (normalInfo.textColor == 0) v.currentTextColor else normalInfo.textColor

        val colors = intArrayOf(stateColor, normalColor)
        val colorStateList = ColorStateList(states, colors)

        v.setTextColor(colorStateList)
    }
}