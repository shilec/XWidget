package com.scott.xwidget.drawable

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.scott.xwidget.drawable.editor.DrawableEditTransaction
import com.scott.xwidget.drawable.editor.IDrawableEditTransaction
import com.scott.xwidget.drawable.editor.IDrawableEditor
import com.scott.xwidget.drawable.render.FirstDrawableRender
import com.scott.xwidget.drawable.render.IRender

/**
 *  gradient drawable with shadow
 */
class GradientDrawableDecorator(private val drawableInfo: DrawableInfo) : GradientDrawable() ,
    IDrawableEditor {
    private val renderHead: IRender = FirstDrawableRender()

    var tag: String? = null

    override fun commit(drawableInfo: DrawableInfo?): IDrawableEditor {
        renderHead.next = null
        this.drawableInfo.merge(drawableInfo)
        DrawableWrapUtils.wrap(this.drawableInfo, this)
        return this
    }

    private val editor =
        DrawableEditTransaction(
            drawableInfo,
            this
        )

    init {
        commit(drawableInfo)
    }

    override fun edit(): IDrawableEditTransaction {
        return editor
    }

    override fun getXDrawableInfo(): DrawableInfo {
        return drawableInfo
    }

    override fun asDrawable(): Drawable {
        return this
    }

    fun addRender(render: IRender) {
        var tail: IRender? = renderHead
        while (tail?.next != null) {
            tail = tail.next
        }
        tail?.next = render
    }

    override fun draw(canvas: Canvas) {
        var head = renderHead.next
        super.draw(canvas)

        while (head != null) {
            head.draw(canvas, bounds)
            head = head.next
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        var head = renderHead.next
        var rect = Rect(left, top, right, bottom)
        while (head != null) {
            rect = head.onBoundsChanged(left, top, right, bottom)
            head = head.next
        }
        super.setBounds(rect.left, rect.top, rect.right, rect.bottom)
    }
}