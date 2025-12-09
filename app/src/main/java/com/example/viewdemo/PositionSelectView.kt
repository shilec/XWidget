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

    private var config: PositionSelectViewConfig = PositionSelectViewConfig()
        set(value) {
            field = value
            updatePaints()
            invalidate()
        }

    /**
     * 设置配置
     */
    fun setConfig(config: PositionSelectViewConfig) {
        this.config = config
    }

    private val touchSize: Float
        get() = config.touchSize
        
    private val touchRect: Rect
        get() = Rect((offsetPoint.x - touchSize).toInt(),
            (offsetPoint.y - touchSize).toInt(), (offsetPoint.x + touchSize).toInt(), (offsetPoint.y + touchSize).toInt()
        )

    private val rectPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private val linePaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }

    private val centerCirclePaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.FILL
    }

    private val offsetCirclePaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.FILL
    }

    private fun updatePaints() {
        rectPaint.color = config.rectBorderColor
        rectPaint.strokeWidth = config.rectBorderWidth
        
        linePaint.color = config.lineColor
        linePaint.strokeWidth = config.lineWidth
        
        centerCirclePaint.color = config.centerCircleColor
        centerCirclePaint.strokeWidth = config.circleRadius
        
        offsetCirclePaint.color = config.offsetCircleColor
        offsetCirclePaint.strokeWidth = config.circleRadius
    }

    init {
        updatePaints()
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

        canvas?.drawCircle(centerX, centerY, config.circleRadius, centerCirclePaint)

        canvas?.drawCircle(offsetX, offsetY, config.circleRadius, offsetCirclePaint)
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