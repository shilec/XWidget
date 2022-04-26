package com.scott.xwidget.drawable

import android.graphics.*
import com.scott.xwidget.utils.ParseUtils

class ShadowDrawableRender(
    private var shadowColor: Int = 0,
    private var corner: Float = 0f,
    private var shadowR: Int = 0,
    private var shadowDx: Float = 0f,
    private var shadowDy: Float = 0f,
    private var corerType: Int = 0,
    private var backgroundColor: Int = Color.WHITE
): IRender {

    companion object {
        const val TAG = "ShadowGradientDrawable"
    }

    private val shadowPaint = Paint()

    private fun drawShadowBackground(canvas: Canvas, bounds: Rect) {
        if (shadowR == 0) {
            return
        }

        val shadowRect = RectF()
        shadowRect.left = bounds.left.toFloat()
        shadowRect.right = bounds.right.toFloat()
        shadowRect.top = bounds.top.toFloat()
        shadowRect.bottom = bounds.bottom.toFloat()

        val path = Path()
        val corners = ParseUtils.getCornersByType(corerType, corner)
        path.addRoundRect(shadowRect, corners, Path.Direction.CW)

        canvas.drawPath(path, shadowPaint)
    }

    override var next: IRender? = null

    override fun draw(canvas: Canvas, bounds: Rect) {
        //val start = System.currentTimeMillis()
        if (shadowR != 0) {
            drawShadowBackground(canvas, bounds)
        }
        // Log.d(TAG, "-- render spend time - ${System.currentTimeMillis() - start}")
    }

    override fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect {
        if (shadowR != 0) {
            val r = (shadowR * 1.5).toInt()
            val rect = Rect(
                ((left + r - shadowDx).toInt()),
                (top + r - shadowDy).toInt(), (right - r - shadowDx).toInt(), (bottom - r - shadowDy).toInt()
            )

            shadowPaint.isAntiAlias = true
            shadowPaint.color = if (backgroundColor == 0) Color.WHITE else backgroundColor
            shadowPaint.style = Paint.Style.FILL

            shadowPaint.setShadowLayer(shadowR.toFloat(), shadowDx, shadowDy, shadowColor)

            return rect
        } else {
            return Rect(left, top, right, bottom)
        }
    }
}