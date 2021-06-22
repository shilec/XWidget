package com.scott.xwidget.drawable

import android.graphics.*
import com.scott.xwidget.utils.ParseUtils

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/6/21 6:11 PM
 * @Description: 边框渐变渲染器
 */
class GradientStrokeRender(private val drawableInfo: DrawableInfo) : IRender {
    override var next: IRender? = null

    private val paint = Paint().let {
        it.style = Paint.Style.STROKE
        it.isAntiAlias = true
        it.strokeWidth = drawableInfo.strokeBorderWith

        it
    }

    // TODO 目前默认只支持linear 类型
    private fun initPaintShader(w: Int) {
        val x0 = if (drawableInfo.strokeGradientOffsetX != 0f) drawableInfo.strokeGradientOffsetX else 0f
        val y0 = if (drawableInfo.strokeGradientOffsetY != 0f) drawableInfo.strokeGradientOffsetY else 0f
        val x1 = if (drawableInfo.strokeGradientOffsetX1 != 0f) drawableInfo.strokeGradientOffsetX1 else w.toFloat()
        val y1 = if (drawableInfo.strokeGradientOffsetY1 != 0f) drawableInfo.strokeGradientOffsetY1 else 0f

        val colorArr = if (drawableInfo.strokeGradientMiddleColor != 0) intArrayOf(drawableInfo.strokeGradientStartColor,
            drawableInfo.strokeGradientMiddleColor, drawableInfo.strokeGradientEndColor) else intArrayOf(drawableInfo.strokeGradientStartColor, drawableInfo.strokeGradientEndColor)
        paint.shader = LinearGradient(x0, y0, x1, y1, colorArr, null, Shader.TileMode.MIRROR)
    }

    override fun draw(canvas: Canvas, bounds: Rect) {
        val path = Path()

        val rectF = RectF()
        rectF.left = bounds.left + drawableInfo.strokeBorderWith
        rectF.top = bounds.top + drawableInfo.strokeBorderWith
        rectF.right = bounds.right - drawableInfo.strokeBorderWith
        rectF.bottom = bounds.bottom - drawableInfo.strokeBorderWith

        val corners = ParseUtils.getCornersByType(drawableInfo.cornerType, drawableInfo.corner)
        path.addRoundRect(rectF, corners, Path.Direction.CW)

        canvas.drawPath(path, paint)
    }

    override fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect {
        initPaintShader(right - left)
        return Rect(left, top, right, bottom)
    }
}