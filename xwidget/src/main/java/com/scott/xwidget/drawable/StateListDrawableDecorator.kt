package com.scott.xwidget.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import com.scott.xwidget.annotation.StateType
import com.scott.xwidget.drawable.editor.DrawableEditorWrapper
import com.scott.xwidget.drawable.editor.IDrawableEditor
import com.scott.xwidget.drawable.editor.IStateDrawableEditor

class StateListDrawableDecorator(override val stateType: Int, val normal: Drawable, val state: Drawable) : StateListDrawable(),
    IStateDrawableEditor {
    init {
        var stateArr: IntArray? = null
        var stateNormal: IntArray? = null

        when (stateType) {
            StateType.TYPE_PRESSED -> {
                stateArr = intArrayOf(android.R.attr.state_pressed)
                stateNormal = intArrayOf(-android.R.attr.state_pressed)
            }
            StateType.TYPE_SELECTED -> {
                stateArr = intArrayOf(android.R.attr.state_selected)
                stateNormal = intArrayOf(-android.R.attr.state_selected)
            }
            StateType.TYPE_CHECKED -> {
                stateArr = intArrayOf(android.R.attr.state_checked)
                stateNormal = intArrayOf(-android.R.attr.state_checked)
            } else -> {
                stateArr = intArrayOf()
                stateNormal = intArrayOf()
            }
        }

        addState(stateArr, state)
        addState(stateNormal, normal)

        if (state is GradientDrawableDecorator) {
            state.tag = "STATE"
            state.xDrawableInfo.currentState = stateType
        }

        if (normal is GradientDrawableDecorator) {
            normal.tag = "NORMAL"
            normal.xDrawableInfo.currentState = StateType.TYPE_NONE
        }
    }

    override val normalEditor: IDrawableEditor
        get() = DrawableEditorWrapper(normal)
    override val stateEditor: IDrawableEditor
        get() = DrawableEditorWrapper(state)

    override fun asDrawable(): Drawable {
        return this
    }
}