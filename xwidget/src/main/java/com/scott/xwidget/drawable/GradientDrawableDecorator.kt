package com.scott.xwidget.drawable

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.scott.xwidget.drawable.editor.DrawableEditTransaction
import com.scott.xwidget.drawable.editor.IDrawableEditTransaction
import com.scott.xwidget.drawable.editor.IDrawableEditor
import com.scott.xwidget.drawable.render.GradientStrokeRender
import com.scott.xwidget.drawable.render.IRender

/**
 *  gradient drawable with shadow
 */
class GradientDrawableDecorator(private val drawableInfo: DrawableInfo) : GradientDrawable() ,
    IDrawableEditor {

    private val topRenderList = arrayListOf<IRender>()
    private val backgroundRenderList = arrayListOf<IRender>()
    var tag: String? = null

    override fun commit(drawableInfo: DrawableInfo?): IDrawableEditor {
        topRenderList.clear()
        backgroundRenderList.clear()
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
        if (render is GradientStrokeRender) {
            topRenderList.add(render)
        } else {
            backgroundRenderList.add(render)
        }
    }

    override fun draw(canvas: Canvas) {
        backgroundRenderList.forEach {
            it.draw(canvas, bounds)
        }

        super.draw(canvas)

        topRenderList.forEach {
            it.draw(canvas, bounds)
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        val all = arrayListOf<IRender>()
        all.addAll(backgroundRenderList)
        all.addAll(topRenderList)

        var rect = Rect(left, top, right, bottom)
        all.forEach {
            rect = it.onBoundsChanged(left, top, right, bottom)
        }

        super.setBounds(rect.left, rect.top, rect.right, rect.bottom)
    }
}