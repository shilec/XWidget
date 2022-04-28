package com.scott.xwidget.drawable.editor

import android.graphics.drawable.Drawable
import com.scott.xwidget.annotation.StateType
import com.scott.xwidget.drawable.GradientDrawableDecorator
import com.scott.xwidget.drawable.RippleDrawableDecorator
import com.scott.xwidget.drawable.StateListDrawableDecorator

/**
 * @Author      :   shijiale
 * @Date        :   2022/4/27 5:48 下午
 * @Email       :   shilec@126.com
 * @Description :
 */
class RippleDrawableStateEditorWrapper(private val drawable: RippleDrawableDecorator) :
    IStateDrawableEditor {
    override val normalEditor: IDrawableEditor
        get() {
            if (drawable.content is StateListDrawableDecorator) {
                val ed = (drawable.content as StateListDrawableDecorator).normalEditor
                // 修改normal的同时修改父容器drawable
                ed.edit().asProxy(drawable.edit())
                return ed
            }

            if (drawable.content is GradientDrawableDecorator) {
                val d = drawable.content as GradientDrawableDecorator
                d.edit().asProxy(drawable.edit())
                return d
            }
            return DrawableEditorWrapper(drawable)
        }
    override val stateEditor: IDrawableEditor
        get() {
            if (drawable.content is StateListDrawableDecorator) {
                val ed = (drawable.content as StateListDrawableDecorator).stateEditor
                ed.edit().asProxy(drawable.edit())
                return ed
            }

            if (drawable.content is GradientDrawableDecorator) {
                val d = drawable.content as GradientDrawableDecorator
                d.edit().asProxy(drawable.edit())
                return d
            }
            return DrawableEditorWrapper(drawable)
        }

    override val stateType: Int
        get() {
            return if (drawable.content is IStateDrawableEditor) {
                (drawable.content as StateListDrawableDecorator).stateType
            } else {
                StateType.TYPE_NONE
            }
        }

    override fun asDrawable(): Drawable {
        return drawable
    }
}