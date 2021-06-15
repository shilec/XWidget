package com.scott.xwidget.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

class StateListDrawableDecorator(val stateType: Int, val normalDrawable: Drawable, val stateDrawable: Drawable) : StateListDrawable() {
    init {
        var stateArr: IntArray? = null
        var stateNormal: IntArray? = null

        when (stateType) {
            1 -> {
                stateArr = intArrayOf(android.R.attr.state_pressed)
                stateNormal = intArrayOf(-android.R.attr.state_pressed)
            }
            2 -> {
                stateArr = intArrayOf(android.R.attr.state_selected)
                stateNormal = intArrayOf(-android.R.attr.state_selected)
            }
            3 -> {
                stateArr = intArrayOf(android.R.attr.state_checked)
                stateNormal = intArrayOf(-android.R.attr.state_checked)
            }
        }

        addState(stateArr, stateDrawable)
        addState(stateNormal, normalDrawable)
    }
    companion object {
        const val STATE_TYPE_PRESSED = 1
        const val STATE_TYPE_SELECTED = 2
        const val STATE_TYPE_CHECKED = 3
        const val STATE_TYPE_NONE = 0
    }
}