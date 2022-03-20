package com.scott.xwidget.drawable

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable

/**
 *  gradient drawable with shadow
 */
class GradientDrawableDecorator(private val drawableInfo: DrawableInfo) : GradientDrawable() ,
    IDrawableEditor {
    private val renderHead: IRender = FirstDrawableRender()

    override fun commit(drawableInfo: DrawableInfo?) {
        mutate()
        renderHead.next = null
        DrawableWrapUtils.wrap(drawableInfo, this)
    }

    private val editor =
        DrawableEditBuilder(drawableInfo, this)

    init {
        commit(drawableInfo)
    }

    override fun newBuilder(): IDrawableEditBuilder {
        return editor
    }

    override fun getXDrawableInfo(): DrawableInfo {
        return drawableInfo
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
        while (head != null) {
            head.draw(canvas, bounds)
            head = head.next
        }
        super.draw(canvas)
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