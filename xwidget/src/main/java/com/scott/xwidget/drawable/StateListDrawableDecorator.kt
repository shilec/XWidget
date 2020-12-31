package com.scott.xwidget.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

class StateListDrawableDecorator(stateType: Int, val normalDrawable: Drawable, val stateDrawable: Drawable) : StateListDrawable() {
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
}