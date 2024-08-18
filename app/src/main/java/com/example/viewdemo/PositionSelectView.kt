package com.example.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2023/10/29 2:50 PM
 * @Description:
 */
class PositionSelectView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var offsetPoint: Point = Point()
        get() {
            if (field.equals(0, 0)) {
                field = Point(
                    fromRect.left + fromRect.width() / 2,
                    fromRect.top + fromRect.height() / 2
                )
            }
            return field
        }
        set(value) {
            field = value
            invalidate()
        }

    var fromRect: Rect = Rect()
        set(value) {
            field = value
            invalidate()
        }

    private val rectBorderColor = Color.parseColor("#FF0000")
    private val rectBorderWidth = 5f

    private val circleR = 20f
    private val centerCircleColor = Color.parseColor("#00FF00")
    private val offsetCircleColor = Color.parseColor("#0000FF")

    private val lineColor = Color.parseColor("#CCCCCC")
    private val lineWidth = 13f

    private val touchSize = 60f
    private val touchRect: Rect
        get() = Rect((offsetPoint.x - touchSize).toInt(),
            (offsetPoint.y - touchSize).toInt(), (offsetPoint.x + touchSize).toInt(), (offsetPoint.y + touchSize).toInt()
        )

    private val rectPaint by lazy {
        val p = Paint()
        p.isAntiAlias = true
        p.color = rectBorderColor
        p.strokeWidth = rectBorderWidth
        p.style = Paint.Style.STROKE
        p
    }

    private val linePaint by lazy {
        val p = Paint()
        p.isAntiAlias = true
        p.strokeCap = Paint.Cap.ROUND
        p.color = lineColor
        p.strokeWidth = lineWidth
        p.style = Paint.Style.STROKE
        p
    }

    private val centerCirclePaint by lazy {
        val p = Paint()
        p.isAntiAlias = true
        p.strokeCap = Paint.Cap.ROUND
        p.color = centerCircleColor
        p.strokeWidth = circleR
        p.style = Paint.Style.FILL
        p
    }

    private val offsetCirclePaint by lazy {
        val p = Paint()
        p.isAntiAlias = true
        p.strokeCap = Paint.Cap.ROUND
        p.color = offsetCircleColor
        p.strokeWidth = circleR
        p.style = Paint.Style.FILL
        p
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (fromRect.isEmpty) {
            return
        }

        canvas?.drawRect(fromRect, rectPaint)

        val centerX = (fromRect.left + fromRect.width() / 2).toFloat()
        val centerY = (fromRect.top + fromRect.height() / 2).toFloat()

        val offsetX = offsetPoint.x.toFloat()
        val offsetY = offsetPoint.y.toFloat()

        canvas?.drawLine(
            centerX,
            centerY,
            offsetX, offsetY, linePaint
        )

        canvas?.drawCircle(centerX, centerY, circleR, centerCirclePaint)

        canvas?.drawCircle(offsetX, offsetY, circleR, offsetCirclePaint)
    }

    private var isTouchInOffsetCircle = false
    private var downX: Float = 0f
    private var downY: Float = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (touchRect.contains(event.x.toInt(), event.y.toInt())) {
                    isTouchInOffsetCircle = true
                    downX = event.x
                    downY = event.y
                }
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                isTouchInOffsetCircle = false
                downX = 0f
                downY = 0f
            }

            MotionEvent.ACTION_MOVE -> {
                if (isTouchInOffsetCircle) {
                    val p = offsetPoint
                    p.x = p.x + ((event.x - downX).toInt())
                    p.y = p.y + ((event.y - downY)).toInt()
                    downX = event.x
                    downY = event.y
                    offsetPoint = p
                    Log.d("Main", "--- offsetPoint = $offsetPoint")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        super.dispatchTouchEvent(event)
        return true
    }
}