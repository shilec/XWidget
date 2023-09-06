package com.scott.xwidget.drawable.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.GradientDrawable
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapePathModel
import com.scott.xwidget.drawable.DrawableInfo

/**
 * @Author      :   shijiale
 * @Date        :   2023/9/6 11:41 AM
 * @Email       :   shilec@126.com
 * @Description :
 */
class GradientMaterialShapeDrawable(
    private val drawableInfo: DrawableInfo,
    shapePathModel: ShapePathModel?
) :
    MaterialShapeDrawable(shapePathModel) {

    private val solidPaint = Paint().apply {
        isAntiAlias = true
        if (drawableInfo.shadowRadius != 0f) {
            setShadowLayer(
                drawableInfo.shadowRadius,
                drawableInfo.shadowDx,
                drawableInfo.shadowDy,
                drawableInfo.shadowColor
            )
        }
        style = Paint.Style.FILL_AND_STROKE
    }

    private val strokePaint = Paint().apply {
        isAntiAlias = true
        color = drawableInfo.strokeBorderColor
        strokeWidth = drawableInfo.strokeBorderWith
        style = Paint.Style.STROKE

        if (drawableInfo.shadowRadius != 0f && (drawableInfo.solidColor == 0 || drawableInfo.gradientStartColor == 0)) {
            setShadowLayer(
                drawableInfo.shadowRadius,
                drawableInfo.shadowDx,
                drawableInfo.shadowDy,
                drawableInfo.shadowColor
            )
        }
    }

    private fun getLinearGradientShader(rect: Rect, drawableInfo: DrawableInfo): Shader {
        val gradientOrientation = DrawableInfo.getOrientation(drawableInfo.gradientOrientation)
        var x0: Float = 0f
        var x1: Float = 0f
        var y0: Float = 0f
        var y1: Float = 0f

        when (gradientOrientation) {
            GradientDrawable.Orientation.LEFT_RIGHT -> {
                x0 = rect.left.toFloat()
                y0 = rect.top.toFloat()
                x1 = rect.right.toFloat()
                y1 = rect.top.toFloat()
            }

            GradientDrawable.Orientation.TOP_BOTTOM -> {
                x0 = rect.left.toFloat()
                y0 = rect.top.toFloat()
                x1 = rect.left.toFloat()
                y1 = rect.bottom.toFloat()
            }

            GradientDrawable.Orientation.TL_BR -> {
                x0 = rect.left.toFloat()
                y0 = rect.top.toFloat()
                x1 = rect.right.toFloat()
                y1 = rect.bottom.toFloat()
            }

            GradientDrawable.Orientation.TR_BL -> {
                x0 = rect.right.toFloat()
                y0 = rect.top.toFloat()
                x1 = rect.left.toFloat()
                y1 = rect.bottom.toFloat()
            }

            else -> {
                x0 = rect.left.toFloat()
                y0 = rect.top.toFloat()
                x1 = rect.right.toFloat()
                y1 = rect.top.toFloat()
            }
        }

        var colorArray = IntArray(2)
        if (drawableInfo.gradientStartColor != Color.TRANSPARENT && drawableInfo.gradientCenterColor != Color.TRANSPARENT && drawableInfo.gradientEndColor != Color.TRANSPARENT) {
            colorArray = intArrayOf(
                drawableInfo.gradientStartColor,
                drawableInfo.gradientCenterColor,
                drawableInfo.gradientEndColor
            )
        } else if (drawableInfo.gradientStartColor != Color.TRANSPARENT && drawableInfo.gradientEndColor != Color.TRANSPARENT) {
            colorArray = intArrayOf(drawableInfo.gradientStartColor, drawableInfo.gradientEndColor)
        } else {
            colorArray[0] = drawableInfo.solidColor
            colorArray[1] = drawableInfo.solidColor
        }

        return LinearGradient(
            x0,
            y0,
            x1,
            y1,
            colorArray,
            null,
            Shader.TileMode.CLAMP
        )
    }

    private val newPath = Path()

    override fun draw(canvas: Canvas) {
        getPathForSize(bounds.width(), bounds.height(), newPath)
        canvas.drawPath(newPath, solidPaint)

        if (drawableInfo.strokeBorderWith != 0f) {
            getPathForSize(
                (bounds.width() - drawableInfo.strokeBorderWith).toInt(),
                (bounds.height() - drawableInfo.strokeBorderWith).toInt(), newPath
            )
            canvas.translate(drawableInfo.strokeBorderWith / 2, drawableInfo.strokeBorderWith / 2)
            canvas.drawPath(newPath, strokePaint)
        }
    }

    override fun onBoundsChange(bounds: Rect?) {
        bounds ?: return
        super.onBoundsChange(bounds)
        solidPaint.shader = getLinearGradientShader(bounds, drawableInfo)
    }
}