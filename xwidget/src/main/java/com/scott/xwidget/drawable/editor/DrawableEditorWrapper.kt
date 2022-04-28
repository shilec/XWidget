package com.scott.xwidget.drawable.editor

import android.graphics.drawable.Drawable
import com.scott.xwidget.annotation.StateType
import com.scott.xwidget.drawable.DrawableInfo
import com.scott.xwidget.drawable.GradientDrawableDecorator
import com.scott.xwidget.drawable.StateListDrawableDecorator

/**
 * @Author      :   shijiale
 * @Date        :   2022/4/27 10:52 上午
 * @Email       :   shilec@126.com
 * @Description :
 */
class StateDrawableWrapper(@StateType override val stateType: Int,
                           override val normalEditor: GradientDrawableDecorator,
                           override val stateEditor: GradientDrawableDecorator
) : IStateDrawableEditor {

    override fun asDrawable(): Drawable {
        return StateListDrawableDecorator(stateType, normalEditor, stateEditor)
    }
}

class DrawableEditorWrapper(private val drawable: Drawable): IDrawableEditor {
    override fun asDrawable(): Drawable {
        return drawable
    }

    override fun commit(drawableInfo: DrawableInfo?): IDrawableEditor {
        if (drawable is IDrawableEditor) {
            return drawable.commit(drawableInfo)
        }
        return this
    }

    override fun edit(): IDrawableEditTransaction {
        if (drawable is IDrawableEditor) {
            return drawable.edit()
        }
        return DrawableEditTransaction(
            DrawableInfo(),
            this
        )
    }

    override fun getXDrawableInfo(): DrawableInfo {
        if (drawable is IDrawableEditor) {
            return drawable.xDrawableInfo
        }
        return DrawableInfo()
    }
}