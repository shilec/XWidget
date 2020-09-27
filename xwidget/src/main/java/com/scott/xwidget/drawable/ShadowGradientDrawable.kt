package com.scott.xwidget.drawable

import android.graphics.*
import android.graphics.drawable.GradientDrawable

/**
 *  gradient drawable with shadow
 */
class ShadowGradientDrawable : GradientDrawable() {
    var shadowColor: Int = 0
    var corner: Float = 0f
    var shadowR: Int = 0
    var shadowDx: Float = 0f
    var shadowDy: Float = 0f

    private val shadowPaint = Paint()

    override fun draw(canvas: Canvas) {
        val shadowRect = RectF()
        shadowRect.left = bounds.left.toFloat()
        shadowRect.right = bounds.right.toFloat()
        shadowRect.top = bounds.top.toFloat()
        shadowRect.bottom = bounds.bottom.toFloat()

        canvas.drawRoundRect(shadowRect, corner, corner, shadowPaint)
        super.draw(canvas)
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(
            (left + shadowR),
            (top + shadowR), right - shadowR, bottom - shadowR)

        shadowPaint.isAntiAlias = true
        shadowPaint.color = Color.WHITE
        shadowPaint.style = Paint.Style.FILL
        shadowPaint.setShadowLayer(shadowR.toFloat(), shadowDx, shadowDy, shadowColor)
    }
}