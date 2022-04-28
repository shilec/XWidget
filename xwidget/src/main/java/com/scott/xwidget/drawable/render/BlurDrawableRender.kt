package com.scott.xwidget.drawable.render

import android.graphics.*
import com.scott.xwidget.utils.ParseUtils

class BlurDrawableRender(private val blurColor: Int = 0,
                         private val blurType: Int = 0,
                         private val corner: Float = 0f,
                         private val blurR: Int = 0,
                         private val corerType: Int = 0) : IRender {

    companion object {
        const val TAG = "BlurDrawableRender"
    }

    private val blurPaint = Paint()

    private fun drawShadowBackground(canvas: Canvas, bounds: Rect) {
        if (blurR == 0)
            return

        val shadowRect = RectF()
        shadowRect.left = bounds.left.toFloat()
        shadowRect.right = bounds.right.toFloat()
        shadowRect.top = bounds.top.toFloat()
        shadowRect.bottom = bounds.bottom.toFloat()

        val path = Path()
        val corners = ParseUtils.getCornersByType(corerType, corner)
        path.addRoundRect(shadowRect, corners, Path.Direction.CW)

        canvas.drawPath(path, blurPaint)
    }

    private fun getBlurType(blurType: Int): BlurMaskFilter.Blur {
        return when (blurType) {
            0 -> BlurMaskFilter.Blur.NORMAL
            1 -> BlurMaskFilter.Blur.OUTER
            2 -> BlurMaskFilter.Blur.SOLID
            3 -> BlurMaskFilter.Blur.INNER
            else -> BlurMaskFilter.Blur.NORMAL
        }
    }

    override var next: IRender? = null

    override fun draw(canvas: Canvas, bounds: Rect) {
        val start = System.currentTimeMillis()
        if (blurR != 0) {
            drawShadowBackground(canvas, bounds)
        }
        // Log.d(TAG, "-- render spend time - ${System.currentTimeMillis() - start}")
    }

    override fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect {
        if (blurR != 0) {
            val r = (blurR * 1.5).toInt()
            val rect = Rect(
                ((left + r)),
                (top + r), (right - r), (bottom - r)
            )

            blurPaint.isAntiAlias = true
            blurPaint.color = blurColor
            blurPaint.style = Paint.Style.FILL

            blurPaint.maskFilter = BlurMaskFilter(blurR.toFloat(), getBlurType(blurType))
            return rect
        } else {
            return Rect(left, top, right, bottom)
        }
    }
}